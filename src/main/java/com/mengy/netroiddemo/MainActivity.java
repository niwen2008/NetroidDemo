package com.mengy.netroiddemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mengy.netroiddemo.base.BaseActivity;
import com.mengy.netroiddemo.bean.DataBean;
import com.mengy.netroiddemo.loadingview.AbsLoadingView;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView listView;
    private MainManager mainManager;
    private MainAdapter mainAdapter;
    private View toolbar_rl;

    @Override
    public void initViews() {
        listView = (ListView) findViewById(R.id.listview);
        toolbar_rl = findViewById(R.id.toolbar_rl);
        toolbar_rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        super.initData();

        mainManager=new MainManager(new AbsLoadingView(listView) {
            @Override
            protected View getLoadingParentView() {
                View rootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
                return rootView;
            }

            @Override
            public void fillData(Object obj) {
                if (obj == null || !(obj instanceof List)) {
                    return;
                }
                mainAdapter.setItems((List<DataBean>) obj);
                mainAdapter.notifyDataSetChanged();
            }
        });
        mainManager.loadData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mainAdapter = new MainAdapter(MainActivity.this);
        listView.setAdapter(mainAdapter);
    }
}
