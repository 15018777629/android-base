package com.yxr.baseandroid.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.BodyRequest;
import com.lzy.okgo.request.base.Request;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.yxr.baseandroid.util.LogUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 封装OkGo的网络访问帮助类，推出界面时建议调用clearDisposable()
 * Created by 63062 on 2017/9/25.
 */

public class HttpHelper {
    private CompositeDisposable compositeDisposable;

    public <T> void obGet(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>get(url), map, null, callBack);
    }

    public <T> void obPost(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>post(url), null, map == null ? "" : GsonInstance.instance().toJson(map), callBack);
    }

    public <T> void obPostJson(String url, String json, final HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>post(url), null, json == null ? "" : json, callBack);
    }

    public <T> void obPut(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>put(url), null, map == null ? "" : GsonInstance.instance().toJson(map), callBack);
    }

    public <T> void obPutJson(String url, String json, final HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>put(url), null, json == null ? "" : json, callBack);
    }

    public <T> void obDelete(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.delete(url), map, null, callBack);
    }

    private <T> void dealRequest(Request request, Map<String, String> map, String json, HttpCallBack<T> callBack) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (json != null && request instanceof BodyRequest) {
            ((BodyRequest) request).upJson(json);
        } else {
            request.params(map);
        }
        request.converter(new StringConvert());
        Observable<Response<String>> adapt = (Observable<Response<String>>) request.adapt(new ObservableResponse<String>());
        adapt.subscribeOn(Schedulers.io());
        adapt.observeOn(AndroidSchedulers.mainThread());
        adapt.subscribe(new HttpObserver(callBack));
    }

    private class HttpObserver implements Observer<Response<String>> {
        private final HttpCallBack callBack;
        public <T> HttpObserver(HttpCallBack<T> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            addDisposable(d);
        }

        @Override
        public void onNext(@io.reactivex.annotations.NonNull Response<String> response) {
            onNextCallBack(response, callBack);
        }

        @Override
        public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            e.printStackTrace();
            if (e instanceof HttpException) {
                HttpException ex = (HttpException) e;
                onErrorCallBack(ex.code(), e.getMessage(), callBack);
            } else if (e instanceof SocketTimeoutException) {
                // 连接超时或没有网络连接
                onErrorCallBack(HttpCode.EXCEPTION_TIME_OUT, "连接超时", callBack);
            } else if (e instanceof UnknownHostException) {
                // 没有网络连接
                onErrorCallBack(HttpCode.EXCEPTION_NO_CONNECT, "没有网络连接", callBack);
            } else {
                onErrorCallBack(HttpCode.EXCEPTION_OTHER, "其他错误", callBack);
            }
        }

        @Override
        public void onComplete() {
        }
    }

    /**
     * 访问网络数据成功
     *
     * @param response ： 回传数据
     * @param callBack ： 回调
     */
    private <T> void onNextCallBack(Response<String> response, HttpCallBack<T> callBack) {
        if (callBack == null) {
            return;
        }
        String body = response.body();
        LogUtil.e("HTTP_HELPER", "http code : " + response.code());
        if (response.code() != 200) {
            onErrorCallBack(response.code(), "服务器异常", callBack);
            return;
        }
        if (callBack.cls == null) {
            callBack.onError(HttpCode.EXCEPTION_OTHER, "数据异常");
            return;
        }
        if (callBack.cls == String.class) {
            callBack.onSuccess((T) body);
            return;
        }
        T t = null;
        try {
            t = (T) GsonInstance.instance().fromJson(body, callBack.cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (t == null) {
            onErrorCallBack(HttpCode.EXCEPTION_DATA_PARSE, "数据解析异常", callBack);
        } else {
            callBack.onSuccess(t);
        }
    }

    /**
     * 访问网络错误
     *
     * @param code     ： 错误码
     * @param errorMsg ： 错误信息
     * @param callBack ： 回调
     */
    private <T> void onErrorCallBack(int code, String errorMsg, HttpCallBack<T> callBack) {
        LogUtil.e("HTTP_HELPER", "onErrorCallBack code : " + code + " ===== message : " + errorMsg);
        if (callBack == null) {
            return;
        }
        callBack.onError(code, errorMsg);
    }

    private void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }
}
