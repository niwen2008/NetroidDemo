package com.mengy.netroiddemo.base;

import com.mengy.netroiddemo.bean.BaseDataType;

/**
 * Created by Mengy on 2016/6/20.
 */
public abstract class AbsLoadHelper {
    private BaseDataType baseDataType;

    public AbsLoadHelper(BaseDataType baseDataType) {
        this.baseDataType = baseDataType;
    }

    public HelperListener getHelperListener(final ILoadingListener iLoadingListener) {
        HelperListener helperListener = new HelperListener() {
            @Override 
            public void onLoadSuccess() {
                iLoadingListener.loadSuccess();
            }

            @Override
            public void onLoadFail(String msg) {
                iLoadingListener.loadFail(msg);
            }
        };
        return helperListener;
    }

    public Object getObj(){
        return DataPool.getInstance().getData(baseDataType);
    }

    public void removeData(){
        DataPool.getInstance().removeData(baseDataType);
    }
    public void createDataPool(Object obj){
        removeData();
        DataPool.getInstance().putData(new DataPool.DataBox(baseDataType, obj));
    }

    public void resetData(Object object) {
        removeData();
        createDataPool(object);
    }

    public interface HelperListener {
        void onLoadSuccess();

        void onLoadFail(String msg);
    }
}
