package com.mengy.netroiddemo.base;

/**
 * Created by Mengy on 2016/6/20.
 */
public abstract interface ILoadingView {
    public abstract void fillData(Object obj);

    public abstract void showLoadingView();

    public abstract void hideLoadingView();
}
