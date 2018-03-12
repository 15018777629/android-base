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
 * Created by 63062 on 2018/2/22.
 */

public abstract class BaseViewModel {
    protected final Context context;
    protected HttpHelper httpHelper;
    protected StatusHelper statusHelper;
    private ViewDataBinding binding;

    public BaseViewModel(@NonNull Context context, HttpHelper httpHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
    }

    public void setBinding(@NonNull ViewDataBinding binding) {
        this.binding = binding;
    }

    public void setStatusHelper(StatusHelper statusHelper) {
        this.statusHelper = statusHelper;
    }

    public <T extends ViewDataBinding> T getBinding() {
        if (binding == null) {
            throw new RuntimeException("Must set binding first");
        }
        return (T) binding;
    }

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

    public void loading() {
        if (statusHelper != null) {
            try {
                statusHelper.loading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissLoading() {
        if (statusHelper != null) {
            try {
                statusHelper.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void exception(int code) {
        if (statusHelper != null) {
            try {
                statusHelper.exception(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    public WeakReference<Activity> getActivityWeakReference() {
        if (context == null || !(context instanceof BaseActivity)) {
            return null;
        }
        return ((BaseActivity) context).getActivityWeakReference();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initBizData();
}
