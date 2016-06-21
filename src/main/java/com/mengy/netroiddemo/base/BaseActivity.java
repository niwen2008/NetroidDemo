package com.mengy.netroiddemo.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);

        initViews();

        initData();
    }

    public void initData() {

    }

    public abstract void initViews();
}
