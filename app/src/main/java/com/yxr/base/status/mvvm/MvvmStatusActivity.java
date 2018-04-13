package com.yxr.base.status.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseStatusActivity;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MvvmStatusActivity extends BaseStatusActivity<BaseViewModel> {
    @Override
    public int contentView() {
        return R.layout.activity_mvvm_status;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setBaseTitle("MVVM BaseStatusActivity");
    }

    @Override
    public void initListener() {

    }

    @Override
    public BaseViewModel initBasePresenter() {
        return new MvvmStatusViewModel(this, null);
    }

    @Override
    public void initData() {

    }

    public static void jumpHere(Context context){
        context.startActivity(new Intent(context, MvvmStatusActivity.class));
    }
}
