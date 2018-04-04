package com.yxr.base.mvp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.IBaseView;
import com.yxr.baseandroid.http.HttpHelper;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVPActivityPresenter extends BasePresenter {

    private IMVPActivityView mvpView;

    public MVPActivityPresenter(@NonNull Context context, IBaseView baseView, HttpHelper httpHelper) {
        super(context, baseView, httpHelper);
    }

    @Override
    public void initListener() {
        // 初始化事件监听(EventBus之类的)
    }

    @Override
    public void initData() {
        // 可以初始化与业务无关的数据
        mvpView = getBaseView();
    }

    @Override
    public void initBizData() {
        // 模拟获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mvpView.showToast("mvp activity init biz data complete!");
            }
        },2500);
    }
}
