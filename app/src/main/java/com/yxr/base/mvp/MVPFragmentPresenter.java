package com.yxr.base.mvp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.IBaseView;
import com.yxr.baseandroid.http.HttpCallBack;
import com.yxr.baseandroid.http.HttpUtil;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVPFragmentPresenter extends BasePresenter {

    private IMVPFragmentView mvpView;

    public MVPFragmentPresenter(@NonNull Context context, IBaseView baseView) {
        super(context, baseView);
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
        mvpView.loadStatus(0);
        // 模拟获取数据
        HttpUtil.obGet("http://op.juhe.cn/onebox/football/league?key=bbdf40a269d0f08936ddb07b076be559&league=%E6%B3%95%E7%94%B2"
                , null, new HttpCallBack<String>(context) {
                    @Override
                    public void onSuccess(String s) {
                        mvpView.loadStatus(1);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mvpView.loadStatus(1);
                    }
                });
    }
}
