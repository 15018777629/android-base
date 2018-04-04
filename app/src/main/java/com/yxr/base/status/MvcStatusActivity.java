package com.yxr.base.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseStatusActivity;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MvcStatusActivity extends BaseStatusActivity {
    @Override
    public int contentView() {
        return R.layout.activity_status;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public BasePresenter initBasePresenter() {
        return null;
    }

    @Override
    public void initData() {
        initBizData();
    }

    private void initBizData() {
        showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoading();
            }
        },2500);
    }
    public static void jumpHere(Context context){
        context.startActivity(new Intent(context, MvcStatusActivity.class));
    }
}
