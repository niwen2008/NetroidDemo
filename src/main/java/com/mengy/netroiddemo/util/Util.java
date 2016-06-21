package com.mengy.netroiddemo.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.mengy.netroiddemo.base.BaseApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 工具类，从资源文件得到数据
 * Created by Mengy on 2016/6/20.
 */
public class Util {

    public static String getUri() {
        Context context = BaseApplication.appContext();
        AssetManager am = context.getAssets();
        String line = "";
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(am.open("url"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
