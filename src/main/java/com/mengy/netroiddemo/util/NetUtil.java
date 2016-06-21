package com.mengy.netroiddemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mengy on 2016/6/8.
 */
public class NetUtil {
    /**
     * 判断是否连接网络
     *
     * @return
     */
    public static boolean netAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if(netWorkInfo.getState()== NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isWifi(Context paramContext)
    {
        NetworkInfo connectivity = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return connectivity!=null && connectivity.getType()==ConnectivityManager.TYPE_WIFI;
    }
}
