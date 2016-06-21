package com.mengy.netroiddemo.base;

import com.mengy.netroiddemo.bean.BaseDataType;

import java.util.HashMap;

/**
 * Created by Mengy on 2016/6/20.
 */
public class DataPool {
    private static DataPool instance;
    private HashMap<BaseDataType,Object> dataContainer=new HashMap<>();
    private DataPool(){}
    public static synchronized DataPool getInstance(){
        if(instance==null) {
            instance=new DataPool();
        }
        return instance;
    }

    public void putData(DataBox dataBox){
        dataContainer.put(dataBox.baseDataType,dataBox.object);
    }

    public void removeData(BaseDataType baseDataType){
        dataContainer.remove(baseDataType);
    }

    public Object getData(BaseDataType baseDataType){
        return dataContainer.get(baseDataType);
    }

    public static class DataBox{
        BaseDataType baseDataType;
        Object object;

        public DataBox(BaseDataType baseDataType, Object object) {
            this.baseDataType = baseDataType;
            this.object = object;
        }
    }
}
