package com.mengy.netroiddemo.http.cache;

import com.mengy.netroiddemo.util.CacheUtil;
import com.mengy.netroiddemo.util.FileUtil;

/**
 * Created by Mengy on 2016/6/20.
 */
public class DataDiskProvider {
    private static DataDiskProvider instance;
    private DataDiskProvider(){}
    public static synchronized DataDiskProvider getInstance() {
        if (instance == null) {
            instance = new DataDiskProvider();
        }
        return instance;
    }

    public void saveNetData(String key,String value){
        FileUtil.initFile(key);
        CacheUtil.writeToFile(key,value);
    }

    public String getNetData(String key){
        return CacheUtil.readFromFile(key);
    }
}
