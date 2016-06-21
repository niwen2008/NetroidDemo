package com.mengy.netroiddemo;

import com.mengy.netroiddemo.base.AbsLoadPresenter;
import com.mengy.netroiddemo.base.DaoListener;
import com.mengy.netroiddemo.base.ILoadingListener;
import com.mengy.netroiddemo.bean.BaseDataType;
import com.mengy.netroiddemo.bean.DataBean;
import com.mengy.netroiddemo.bean.MainDataType;
import com.mengy.netroiddemo.http.NetDao;
import com.mengy.netroiddemo.loadingview.AbsLoadingView;

/**
 * 连接MainActivity（view）与底层（model），相当于presenter
 * Created by Mengy on 2016/6/20.
 */
public class MainManager extends AbsLoadPresenter{
    private AbsListHelper absListHelper=new AbsListHelper(MainDataType.PAGERLIST) {
        @Override
        protected void request(DaoListener listener) {
            NetDao.getMainData(listener, DataBean.class);
        }
    };
    public MainManager(AbsLoadingView absLoadingView) {
        super(absLoadingView);
    }

    @Override
    public BaseDataType getLoadType() {
        return MainDataType.PAGERLIST;
    }

    @Override
    public void setLoadType(LoadType loadType, ILoadingListener iLoadingListener) {
        switch (loadType.ordinal()){
            default:
                break;
            case 1:
                absListHelper.requestData(absListHelper.getHelperListener(iLoadingListener));
                break;
        }
    }
}
