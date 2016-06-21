package com.mengy.netroiddemo.util;

import android.os.Environment;
import android.text.TextUtils;

import com.mengy.netroiddemo.base.BaseApplication;

import java.io.File;

/**
 * Created by Mengy on 2016/6/20.
 */
public class FileUtil {
    public static String initFile(String str) {
        StringBuilder sb = new StringBuilder();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            sb.append(createSDCard());
        } else {
            sb.append(createSDCard());
        }
        if(!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(File.separator);
        }
        String path=sb.toString();
        if(createFile(path)){
            return path;
        }
        return null;
    }

    private static boolean createFile(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            return file.mkdirs();
        }
        return false;
    }

    private static String createCache() {
        StringBuilder sb = new StringBuilder();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(file.getAbsoluteFile());
        sb.append(File.separator);
        sb.append("NETROIDDEMO");
        sb.append(File.separator);
        return sb.toString();
    }

    private static String createSDCard() {
        File cacheDir = BaseApplication.appContext().getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return cacheDir.getAbsolutePath() + "/";
    }
}
