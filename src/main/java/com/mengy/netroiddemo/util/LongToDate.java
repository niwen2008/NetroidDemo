package com.mengy.netroiddemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mengy on 2016/6/17.
 */
public class LongToDate {


    public static String stringToDate(String lo){
        Long time=Long.parseLong(lo);
        Date date=new Date(time);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String longToDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
}
