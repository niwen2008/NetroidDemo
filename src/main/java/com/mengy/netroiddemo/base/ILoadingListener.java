package com.mengy.netroiddemo.base;

/**
 * Created by Mengy on 2016/6/20.
 */
public abstract interface ILoadingListener {
    public abstract void loadSuccess();
    public abstract void loadFail(String msg);
}
