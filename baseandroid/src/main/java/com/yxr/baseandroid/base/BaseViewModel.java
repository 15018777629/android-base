package com.yxr.baseandroid.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.helper.StatusHelper;
import com.yxr.baseandroid.http.HttpCallBack;
import com.yxr.baseandroid.http.HttpHelper;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * VM层级封装
 * Created by 63062 on 2018/2/22.
 */

public abstract class BaseViewModel {
    protected final Context context;
    protected HttpHelper httpHelper;
    private StatusHelper statusHelper;
    private ViewDataBinding binding;

    public BaseViewModel(@NonNull Context context, HttpHelper httpHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
    }

    /**
     * 设置ViewDataBinding
     * @param binding
     */
    public final void setBinding(@NonNull ViewDataBinding binding) {
        this.binding = binding;
    }

    public void setStatusHelper(StatusHelper statusHelper) {
        this.statusHelper = statusHelper;
        if (context != null){
            setStatusMarginTop((int) (context.getResources().getDisplayMetrics().density * 48));
        }
    }

    /**
     * @return ：获取ViewDataBinding
     */
    public <T extends ViewDataBinding> T getBinding() {
        if (binding == null) {
            throw new RuntimeException("Must set binding first");
        }
        return (T) binding;
    }

    /**
     * Get网络请求
     * @param url ：请求地址
     * @param map ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obGet(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obGet(url, map, callBack);
        }
    }

    /**
     * Post网络请求
     * @param url ：请求地址
     * @param map ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obPost(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPost(url, map, callBack);
        }
    }

    /**
     * 参数为Json的Post网络请求
     * @param url ：请求地址
     * @param json ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obPostJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPostJson(url, json, callBack);
        }
    }

    /**
     * Put网络请求
     * @param url ：请求地址
     * @param map ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obPut(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPut(url, map, callBack);
        }
    }

    /**
     * 参数为Json的Put网络请求
     * @param url ：请求地址
     * @param json ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obPutJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPutJson(url, json, callBack);
        }
    }

    /**
     * delete网络请求
     * @param url ：请求地址
     * @param map ：请求参数
     * @param callBack ：请求回调
     * @param <T> ：返回数据类型
     */
    public <T> void obDelete(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obDelete(url, map, callBack);
        }
    }

    /**
     * 设置状态内部控件顶部margin
     * @param marginTop
     */
    public void setStatusMarginTop(int marginTop){
        if (statusHelper != null) {
            try {
                statusHelper.setMarginTop(marginTop);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loading 状态
     */
    public void loading() {
        if (statusHelper != null) {
            try {
                statusHelper.loading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取消任何状态
     */
    public void dismissLoading() {
        if (statusHelper != null) {
            try {
                statusHelper.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 异常状态
     * @param code ：异常码（根据{@link com.yxr.baseandroid.http.HttpCode 判断是否网络异常展示空布局或者网络异常布局}）
     */
    public void exception(int code) {
        if (statusHelper != null) {
            try {
                statusHelper.exception(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public StatusHelper getStatusHelper(){
        return statusHelper;
    }

    /**
     * 获取当前对应的Activity的弱引用
     * @return
     */
    public WeakReference<Activity> getActivityWeakReference() {
        if (context == null || !(context instanceof BaseActivity)) {
            return null;
        }
        return ((BaseActivity) context).getActivityWeakReference();
    }

    public void onDestroy() {
        dismissLoading();
        if (httpHelper != null) {
            httpHelper.clearDisposable();
            httpHelper = null;
        }
        if (binding != null) {
            binding.unbind();
            binding = null;
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
