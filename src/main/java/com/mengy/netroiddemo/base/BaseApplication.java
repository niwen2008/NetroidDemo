package com.mengy.netroiddemo.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mengy.netroiddemo.http.HttpUtils;
import com.mengy.netroiddemo.util.ImgHelper;

/**
 * Created by Mengy on 2016/6/20.
 */
public class BaseApplication extends Application {
    private static BaseApplication context;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this, ImgHelper.getImagePipelineConfig(this));
        HttpUtils.init(this);
        context=this;
    }

    public static Context appContext(){
        return context;
    }
}
