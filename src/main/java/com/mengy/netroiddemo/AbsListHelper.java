package com.mengy.netroiddemo;

import android.content.Context;
import android.widget.Toast;

import com.mengy.netroiddemo.base.AbsLoadHelper;
import com.mengy.netroiddemo.base.BaseApplication;
import com.mengy.netroiddemo.base.DaoListener;
import com.mengy.netroiddemo.bean.BaseDataType;
import com.mengy.netroiddemo.bean.EntityArray;
import com.mengy.netroiddemo.bean.NetResponse;
import com.mengy.netroiddemo.util.NetUtil;

import java.util.List;

/**
 * Created by Mengy on 2016/6/20.
 * 和网络请求层交互类
 */
public abstract class AbsListHelper extends AbsLoadHelper {
    private Context context = BaseApplication.appContext();

    public AbsListHelper(BaseDataType baseDataType) {
        super(baseDataType);
    }

    public void requestData(final HelperListener helperListener) {
        if (!NetUtil.netAvailable(context)) {//网络未连接
            Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }
        DaoListener listener = new DaoListener() {
            @Override
            public void onSuccess(NetResponse response) {
                if (response.Variables == null) {
                    return;
                }
                Object variables = response.Variables;
                if(variables instanceof EntityArray) {
                    List list = ((EntityArray) variables).list;
                    resetData(list);
                }
                helperListener.onLoadSuccess();
            }

            @Override
            public void onFail(String msg) {
                helperListener.onLoadFail(msg);
            }
        };
        request(listener);
    }

    protected abstract void request(DaoListener listener);

}
