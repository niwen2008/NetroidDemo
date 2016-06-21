package com.mengy.netroiddemo.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 简单列表适配器.
 * 
 * @author lfy 
 * @param <T>
 */
public abstract class BaseSingleTypeAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mItems;

    public BaseSingleTypeAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据项.
     * @param items
     */
    public void setItems(List<T> items) {
        this.mItems = items;
    }
    
    /**
     * 添加数据项.
     * @param items
     */
    public void addItems(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (mItems == null) {
            mItems = items;
        } else {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }
    
    /**
     * 获取数据项.
     * @return
     */
    public List<T> getItems() {
        return this.mItems;
    }

    @Override
    public int getCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        if (mItems == null)
            return null;
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
