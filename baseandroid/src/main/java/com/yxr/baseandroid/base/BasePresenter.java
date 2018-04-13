package com.yxr.baseandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.base.ui.BaseActivity;
import com.yxr.baseandroid.base.ui.IStatusUi;
import com.yxr.baseandroid.base.ui.ITitleBarUi;
import com.yxr.baseandroid.http.HttpUtil;
import com.yxr.baseandroid.util.ToastUtil;

import java.lang.ref.WeakReference;

/**
 * 业务基类
 * Created by 63062 on 2018/4/2.
 */

public abstract class BasePresenter {
    private final IBaseView baseView;
    protected final Context context;
    protected ITitleBarUi iTitleBar;
    protected IStatusUi iStatus;

    public BasePresenter(@NonNull Context context, IBaseView baseView) {
        this.context = context;
        this.baseView = baseView;
    }

    public <T extends Context> T getContext() {
        return (T) context;
    }

    public <T extends IBaseView> T getBaseView() {
        return baseView == null ? null : (T) baseView;
    }

    public void setTitleAndStatusInt(ITitleBarUi iTitleBar, IStatusUi iStatus) {
        this.iTitleBar = iTitleBar;
        this.iStatus = iStatus;
    }

    public void onResume() { }

    public void onStart() { }

    public void onPause() { }

    public void onStop() { }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onDestroy() {
        HttpUtil.clearDisposable(context);
    }

    /**
     * 获取当前对应的Activity的弱引用
     *
     * @return
     */
    public WeakReference<Activity> getActivityWeakReference() {
        if (context == null || !(context instanceof BaseActivity)) {
            return null;
        }
        return ((BaseActivity) context).getActivityWeakReference();
    }

    public void toast(String msg){
        ToastUtil.toast(context, msg);
    }

    /**
     * 初始化事件
     */
    public abstract void initListener();

    /**
     * 初始化数据
     */
    public abstract void initData();


    /**
     * 初始化业务数据
     */
    public abstract void initBizData();

}
