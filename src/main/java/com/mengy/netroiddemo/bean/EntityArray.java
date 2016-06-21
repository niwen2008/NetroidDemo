package com.mengy.netroiddemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mengy on 2016/6/17.
 */
public class EntityArray<E>
{
    @SerializedName(value="list")
    public List<E> list;
    @SerializedName(value="member_uid")
    public String member_uid;
    @SerializedName(value="member_username")
    public String member_username;
    @SerializedName(value="formhash")
    public String formhash;
}