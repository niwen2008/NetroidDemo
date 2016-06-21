package com.mengy.netroiddemo.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.ImageView;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.RequestQueue;
import com.duowan.mobile.netroid.cache.DiskCache;
import com.duowan.mobile.netroid.image.NetworkImageView;
import com.duowan.mobile.netroid.stack.HttpClientStack;
import com.duowan.mobile.netroid.stack.HttpStack;
import com.duowan.mobile.netroid.stack.HurlStack;
import com.duowan.mobile.netroid.toolbox.BasicNetwork;
import com.duowan.mobile.netroid.toolbox.FileDownloader;
import com.duowan.mobile.netroid.toolbox.ImageLoader;

import java.io.File;

/**
 * Created by Mengy on 2016/6/9.
 */
public class Netroid {
    // Netroid入口，私有该实例，提供方法对外服务。
    private static RequestQueue mRequestQueue;

    // 图片加载管理器，私有该实例，提供方法对外服务。
    private static ImageLoader mImageLoader;

    // 文件下载管理器，私有该实例，提供方法对外服务。
    private static FileDownloader mFileDownloader;

    private Netroid() {
    }

    public static RequestQueue newRequestQueue(Context context){
        String userAgent="httpRes/0";
        try {
            String str= context.getPackageName();
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(str,0);
            userAgent=str+"/"+packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        HttpStack httpStack=null;
        if(Build.VERSION.SDK_INT>=9) {
            httpStack = new HurlStack(userAgent, null);
        }else{
            httpStack = new HttpClientStack(userAgent);
        }
        RequestQueue httpRes = new RequestQueue(new BasicNetwork(httpStack, "UTF-8"), 4, new DiskCache(new File(context.getCacheDir(),
                "httpRes"), 52428800));
        httpRes.start();
        return httpRes;
    }

    public static RequestQueue newRequestQueue(Context context,DiskCache diskCache){
        String userAgent="httpRes/0";
        try {
            String str= context.getPackageName();
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(str,0);
            userAgent=str+"/"+packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        HttpStack httpStack=null;
        if(Build.VERSION.SDK_INT>=9) {
            httpStack = new HurlStack(userAgent, null);
        }else{
            httpStack = new HttpClientStack(userAgent);
        }
        RequestQueue httpRes = new RequestQueue(new BasicNetwork(httpStack, "UTF-8"), 4, diskCache);
        httpRes.start();
        return httpRes;
    }
    // 加载单张图片
    public static void displayImage(String url, ImageView imageView) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, 0, 0);
        mImageLoader.get(url, listener, 0, 0);
    }

    // 批量加载图片
    public static void displayImage(String url, NetworkImageView imageView) {
        imageView.setImageUrl(url, mImageLoader);
    }

    // 执行文件下载请求
    public static FileDownloader.DownloadController addFileDownload(String storeFilePath, String url, Listener<Void> listener) {
        return mFileDownloader.add(storeFilePath, url, listener);
    }

    class Const {
        // http parameters
        public static final int HTTP_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 2MB
        public static final int HTTP_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
        public static final String HTTP_DISK_CACHE_DIR_NAME = "netroid";
        public static final String USER_AGENT = "netroid.cn";
    }
}