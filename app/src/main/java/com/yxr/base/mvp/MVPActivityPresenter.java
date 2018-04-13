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

public class MVPActivityPresenter extends BasePresenter {

    private IMVPActivityView mvpView;

    public MVPActivityPresenter(@NonNull Context context, IBaseView baseView) {
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
        // 模拟获取数据
        HttpUtil.obGet("http://op.juhe.cn/onebox/football/league?key=bbdf40a269d0f08936ddb07b076be559&league=%E6%B3%95%E7%94%B2"
                , null, new HttpCallBack<String>(context) {
            @Override
            public void onSuccess(String s) {
                mvpView.showToast("mvp activity init biz data complete!");
            }

            @Override
            public void onError(int code, String msg) {
                mvpView.showToast("mvp activity init biz data error!");
            }
        });
    }
}
