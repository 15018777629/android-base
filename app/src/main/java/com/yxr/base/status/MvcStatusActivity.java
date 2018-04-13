package com.yxr.base.status;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseStatusActivity;
import com.yxr.baseandroid.http.HttpCode;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MvcStatusActivity extends BaseStatusActivity {
    private TextView tvContent;

    @Override
    public int contentView() {
        return R.layout.activity_status;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvContent = findViewById(R.id.tvContent);
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
                tvContent.setText("init complete!!");
                dismissLoading();
                dismissLoading(true, HttpCode.EXCEPTION_TIME_OUT);
            }
        },2500);
    }
    public static void jumpHere(Context context){
        context.startActivity(new Intent(context, MvcStatusActivity.class));
    }
}
