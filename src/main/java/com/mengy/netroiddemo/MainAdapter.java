package com.mengy.netroiddemo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mengy.netroiddemo.base.BaseSingleTypeAdapter;
import com.mengy.netroiddemo.bean.DataBean;
import com.mengy.netroiddemo.util.LongToDate;

/**
 * 适配器
 * Created by Mengy on 2016/6/21.
 */
public class MainAdapter extends BaseSingleTypeAdapter<DataBean> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_main, null);
            viewHolder.item_image= (SimpleDraweeView) convertView.findViewById(R.id.item_image);
            viewHolder.item_home_time= (TextView) convertView.findViewById(R.id.item_home_time);
            viewHolder.item_home_title= (TextView) convertView.findViewById(R.id.item_home_title);
            viewHolder.user_name= (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.user_avatar= (DraweeView) convertView.findViewById(R.id.user_avatar);
            viewHolder.blog_label_fl= (FrameLayout) convertView.findViewById(R.id.blog_label);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        DataBean listBean=getItem(position);
        Uri uri=Uri.parse(listBean.getThumb());
        viewHolder.item_image.setImageURI(uri);
        viewHolder.item_home_time.setText(LongToDate.stringToDate(listBean.getDateline()));
        viewHolder.item_home_title.setText(listBean.getSubject());
        viewHolder.user_name.setText(listBean.getUsername());
        Uri uri1=Uri.parse(listBean.getMiddle_avatar());
        Log.e("TAG", "uri1="+uri1);
        viewHolder.user_avatar.setImageURI(uri1);
        String hot = listBean.getHot();
        if("1".equals(hot.trim())) {
            viewHolder.blog_label_fl.setVisibility(View.VISIBLE);
        }else {
            viewHolder.blog_label_fl.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        SimpleDraweeView item_image;
        TextView item_home_time;
        TextView item_home_title;
        TextView user_name;
        DraweeView user_avatar;
        FrameLayout blog_label_fl;
    }
}
