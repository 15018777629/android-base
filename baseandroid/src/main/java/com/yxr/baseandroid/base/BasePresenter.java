package com.yxr.baseandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.base.ui.BaseActivity;
import com.yxr.baseandroid.http.HttpCallBack;
import com.yxr.baseandroid.http.HttpHelper;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * 业务基类
 * Created by 63062 on 2018/4/2.
 */

public abstract class BasePresenter {
    private final IBaseView baseView;
    protected final Context context;
    protected HttpHelper httpHelper;

    public BasePresenter(@NonNull Context context, IBaseView baseView, HttpHelper httpHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
        this.baseView = baseView;
    }

    public <T extends IBaseView> T getBaseView() {
        return baseView == null ? null : (T) baseView;
    }

    /**
     * Get网络请求
     *
     * @param url      ：请求地址
     * @param map      ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obGet(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obGet(url, map, callBack);
        }
    }

    /**
     * Post网络请求
     *
     * @param url      ：请求地址
     * @param map      ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obPost(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPost(url, map, callBack);
        }
    }

    /**
     * 参数为Json的Post网络请求
     *
     * @param url      ：请求地址
     * @param json     ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obPostJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPostJson(url, json, callBack);
        }
    }

    /**
     * Put网络请求
     *
     * @param url      ：请求地址
     * @param map      ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obPut(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPut(url, map, callBack);
        }
    }

    /**
     * 参数为Json的Put网络请求
     *
     * @param url      ：请求地址
     * @param json     ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obPutJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPutJson(url, json, callBack);
        }
    }

    /**
     * delete网络请求
     *
     * @param url      ：请求地址
     * @param map      ：请求参数
     * @param callBack ：请求回调
     * @param <T>      ：返回数据类型
     */
    public <T> void obDelete(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obDelete(url, map, callBack);
        }
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


    public void onResume() {

    }

    public void onStart() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void onDestroy() {
        if (httpHelper != null) {
            httpHelper.clearDisposable();
            httpHelper = null;
        }
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
