package com.yxr.baseandroid.http;

import io.reactivex.annotations.NonNull;

/**
 * 访问网络回调
 * Created by 63062 on 2017/9/25.
 */

public abstract class HttpCallBack<T> {
    public Class cls = null;

    /**
     * 访问网络回调
     * @param <T> ： 目标实例对象
     */
    public <T> HttpCallBack(){
        try {
            this.cls = GenericsUtils.getSuperClassGenricType(getClass());
        }catch (Exception e){
            e.printStackTrace();
            this.cls = String.class;
        }
    }

    public abstract void onSuccess(@NonNull T t);

    public abstract void onError(int code, @NonNull String msg);
}
