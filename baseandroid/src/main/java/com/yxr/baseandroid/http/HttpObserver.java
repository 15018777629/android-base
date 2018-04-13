package com.yxr.baseandroid.http;

import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.socks.library.KLog;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HttpObserver implements Observer<Response<String>> {
    private static final String TAG = "HTTP_OBSERVER";
    private HttpCallBack callBack;
    private String url;

    public <T> HttpObserver(HttpCallBack<T> callBack, String url) {
        this.callBack = callBack;
        this.url = url;
    }

    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
        if (callBack != null && callBack.getHashTag() != null){
            HttpUtil.addDisposable(callBack.getHashTag(), d);
        }
    }

    @Override
    public void onNext(@io.reactivex.annotations.NonNull Response<String> response) {
        KLog.i(TAG, url);
        KLog.json(TAG, response.body());
        HttpCallBackUtil.onNextCallBack(url, response, callBack);
    }

    @Override
    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException ex = (HttpException) e;
            HttpCallBackUtil.onErrorCallBack(url, ex.code(), e.getMessage(), callBack);
        } else if (e instanceof SocketTimeoutException) {
            // 连接超时或没有网络连接
            HttpCallBackUtil.onErrorCallBack(url, HttpCode.EXCEPTION_TIME_OUT, "连接超时", callBack);
        } else if (e instanceof UnknownHostException) {
            // 没有网络连接
            HttpCallBackUtil.onErrorCallBack(url, HttpCode.EXCEPTION_NO_CONNECT, "没有网络连接", callBack);
        } else {
            HttpCallBackUtil.onErrorCallBack(url, HttpCode.EXCEPTION_OTHER, "其他错误", callBack);
        }
    }

    @Override
    public void onComplete() {
    }
}
