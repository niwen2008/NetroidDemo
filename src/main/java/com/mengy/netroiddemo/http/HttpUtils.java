package com.mengy.netroiddemo.http;

import android.content.Context;

import com.duowan.mobile.netroid.RequestQueue;

/**
 * Created by Mengy on 2016/6/9.
 */
public class HttpUtils {

    private static RequestQueue mResQueue;

    public static RequestQueue getmResQueue() {
        if (mResQueue != null) {
            return mResQueue;
        }
        throw new IllegalStateException("RequestQueue not initialized");
    }


    public static void init(Context context) {
        mResQueue = Netroid.newRequestQueue(context);
    }
}
