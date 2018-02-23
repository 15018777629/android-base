package com.yxr.baseandroid.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.yxr.baseandroid.http.HttpCallBack;
import com.yxr.baseandroid.http.HttpHelper;

import java.util.Map;

/**
 * Created by 63062 on 2018/2/22.
 */

public abstract class BaseViewModel {
    protected final Context context;
    protected HttpHelper httpHelper;
    private ViewDataBinding binding;

    public BaseViewModel(@NonNull Context context, HttpHelper httpHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }

    public <T extends ViewDataBinding> T getBinding(){
        return (T) binding;
    }

    public abstract void initListener();

    public abstract void initData();

    public <T> void obGet(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obGet(url, map, callBack);
        }
    }

    public <T> void obPost(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPost(url, map, callBack);
        }
    }

    public <T> void obPostJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPostJson(url, json, callBack);
        }
    }

    public <T> void obPut(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPut(url, map, callBack);
        }
    }

    public <T> void obPutJson(String url, String json, final HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obPutJson(url, json, callBack);
        }
    }

    public <T> void obDelete(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        if (httpHelper != null) {
            httpHelper.obDelete(url, map, callBack);
        }
    }

    public void onDestroy() {
        if (httpHelper != null) {
            httpHelper.clearDisposable();
            httpHelper = null;
        }
    }
}
