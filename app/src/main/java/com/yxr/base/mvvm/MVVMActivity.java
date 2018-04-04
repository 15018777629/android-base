package com.yxr.base.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseActivity;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMActivity extends BaseActivity<BaseViewModel> {
    @Override
    public int contentView() {
        return R.layout.activity_mvvm;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public BaseViewModel initBasePresenter() {
        return new MVVMActivityViewModel(this, null);
    }

    @Override
    public void initData() {

    }

    public static void jumpHere(Context context){
        context.startActivity(new Intent(context, MVVMActivity.class));
    }
}
