package com.mengy.netroiddemo.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mengy on 2016/6/17.
 */
public class NetResponse<T> {

    @SerializedName(value = "Version")
    public String Version;
    @Expose
    @SerializedName(value = "Charset")
    public String Charset;

    @Expose
    @SerializedName(value = "Variables")
    public T Variables;

}
