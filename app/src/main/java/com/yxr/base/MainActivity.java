package com.yxr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yxr.baseandroid.base.BaseActivity;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.http.HttpHelper;

public class MainActivity extends BaseActivity {

    @Override
    public int contentView() {
        return R.layout.activity_main;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new MainViewModel(this, new HttpHelper());
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }
}
