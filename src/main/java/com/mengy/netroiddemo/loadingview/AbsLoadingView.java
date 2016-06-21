package com.mengy.netroiddemo.loadingview;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mengy.netroiddemo.R;
import com.mengy.netroiddemo.base.ILoadingView;

/**
 * Created by Mengy on 2016/6/20.
 */
public abstract class AbsLoadingView implements ILoadingView {

    private ViewGroup frameView;
    private ListView listView;
    private View loadingView;
    private View loading_ll;

    public AbsLoadingView(ListView listView) {
        this.listView = listView;
        this.frameView = (ViewGroup) getLoadingParentView();
    }

    protected abstract View getLoadingParentView();

    @Override
    public void hideLoadingView() {
        if (this.listView != null) {
            this.listView.setVisibility(View.VISIBLE);
        }
        loadingView.setVisibility(View.GONE);
        ((AnimationDrawable) loading_ll.getBackground()).stop();
    }

    @Override
    public void showLoadingView() {
        if (this.listView != null) {//隐藏页面内容视图
            this.listView.setVisibility(View.GONE);
        }
        loadingView = LayoutInflater.from(this.frameView.getContext()).inflate(R.layout.layout_loading, null);
        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {//添加loading视图
                frameView.addView(loadingView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        });
        loadingView.setVisibility(View.VISIBLE);
        loading_ll = loadingView.findViewById(R.id.loading_ll);
        ((AnimationDrawable) loading_ll.getBackground()).start();//开启loading动画
    }
}
