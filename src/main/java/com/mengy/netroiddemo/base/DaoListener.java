package com.mengy.netroiddemo.base;

import com.mengy.netroiddemo.bean.NetResponse;

/**
 * Created by Mengy on 2016/6/20.
 */
public interface DaoListener {
    public abstract void onSuccess(NetResponse obj);
    public abstract void onFail(String msg);
}
