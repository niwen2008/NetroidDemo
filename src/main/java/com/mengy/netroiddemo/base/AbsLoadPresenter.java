package com.mengy.netroiddemo.base;

import android.util.Log;

import com.mengy.netroiddemo.bean.BaseDataType;

/**
 * Created by Mengy on 2016/6/20.
 */
public abstract class AbsLoadPresenter {
    public ILoadingView iLoadingView;
    private ILoadingListener iLoadingListener;

    public AbsLoadPresenter(ILoadingView iLoadingView) {
        this.iLoadingView = iLoadingView;
    }

    public void loadData() {
        Log.e("TAG", "loadData");
        if (iLoadingView != null) {
            iLoadingView.showLoadingView();
        }
        if (iLoadingListener == null) {
            iLoadingListener = new ILoadingListener() {
                @Override
                public void loadSuccess() {
                    iLoadingView.hideLoadingView();
                    iLoadingView.fillData(getData(getLoadType()));
                }

                @Override
                public void loadFail(String msg) {
                    iLoadingView.hideLoadingView();
                }
            };
        }
        setLoadType(LoadType.LOAD, iLoadingListener);
    }

    public abstract BaseDataType getLoadType();

    private Object getData(BaseDataType baseDataType) {
        return DataPool.getInstance().getData(baseDataType);
    }

    public abstract void setLoadType(LoadType loadType, ILoadingListener iLoadingListener);

    public enum LoadType {
        REFRESH, LOAD;
    }
}
