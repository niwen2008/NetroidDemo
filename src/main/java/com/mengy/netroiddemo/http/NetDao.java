package com.mengy.netroiddemo.http;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.NetroidError;
import com.duowan.mobile.netroid.Request;
import com.duowan.mobile.netroid.request.StringRequest;
import com.mengy.netroiddemo.base.BaseApplication;
import com.mengy.netroiddemo.base.DaoListener;
import com.mengy.netroiddemo.bean.EntityArray;
import com.mengy.netroiddemo.bean.NetResponse;
import com.mengy.netroiddemo.http.cache.DataDiskProvider;
import com.mengy.netroiddemo.util.MD5;
import com.mengy.netroiddemo.util.NetUtil;
import com.mengy.netroiddemo.util.Util;

import java.util.List;

/**
 * 处理网络请求的底层类
 * Created by Mengy on 2016/6/20.
 */
public class NetDao {
    public static void getMainData(final DaoListener listener, final Class type){
        final String url= Util.getUri();//得到网络请求api
        if(TextUtils.isEmpty(url)) {
            return;
        }
        if(!NetUtil.netAvailable(BaseApplication.appContext())) {//如果没有网络，从缓存读取结果数据
            getDataFromCache(url, listener, type);
        }
        getDataFromNet(listener, type, url);
    }

    /**
     * 联网获取数据
     * @param listener 回调接口，提供给上层处理结果数据
     * @param type 解析数据的Class名称
     * @param url 请求api
     */
    private static void getDataFromNet(final DaoListener listener, final Class type, final String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Listener<String>() {
            @Override
            public void onSuccess(String str) {
                if(str==null||"".equals(str)) {
                    return;
                }
                NetResponse netResponse=new NetResponse();
                JSONObject jsonObj = JSON.parseObject(str);
                Object variables = jsonObj.get("Variables");
                netResponse.Variables=variables;
                JSONArray array= ((JSONObject)variables).getJSONArray("list");
                if(array!=null&&array.size()>0) {
                    EntityArray entityArray=new EntityArray();
                    netResponse.Variables=entityArray;
                    List list = JSONArray.parseArray(String.valueOf(array), type);
                    entityArray.list=list;
                    DataDiskProvider.getInstance().saveNetData(MD5.md5(url),str);
                    listener.onSuccess(netResponse);
                }
            }

            @Override
            public void onError(NetroidError error) {
                super.onError(error);
                listener.onFail(error.getMessage());
            }
        });
        HttpUtils.getmResQueue().add(stringRequest);
    }

    /**
     * 直接读取缓存的数据
     * @param url 请求api
     * @param listener 回调接口，提供给上层处理结果数据
     * @param type 解析数据的Class名称
     */
    public static void getDataFromCache(String url,DaoListener listener,Class type){
        String result=DataDiskProvider.getInstance().getNetData(MD5.md5(url));
        if(result!=null&&!"".equals(result)) {//缓存获取到了数据，处理数据；获取不到数据，从网络获取
            NetResponse netResponse=new NetResponse();
            JSONObject jsonObj = JSON.parseObject(result);
            Object variables = jsonObj.get("Variables");
            netResponse.Variables=variables;
            JSONArray array= ((JSONObject)variables).getJSONArray("list");
            if(array!=null&&array.size()>0) {
                EntityArray entityArray=new EntityArray();
                netResponse.Variables=entityArray;
                List list = JSONArray.parseArray(String.valueOf(array), type);
                entityArray.list=list;
                DataDiskProvider.getInstance().saveNetData(MD5.md5(url), result);
                listener.onSuccess(netResponse);
            }
            return;
        }
    }
}
