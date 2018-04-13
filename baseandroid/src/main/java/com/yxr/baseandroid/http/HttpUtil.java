package com.yxr.baseandroid.http;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.BodyRequest;
import com.lzy.okgo.request.base.Request;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.socks.library.KLog;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

public class HttpUtil {
    public static final String TAG = "HTTP_UTIL";
    private static Map<String, CompositeDisposable> compositeDisposableMap = new ConcurrentHashMap<>();

    private HttpUtil(){}

    public static  <T> void obGet(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>get(url), map, null, callBack);
    }

    public static  <T> void obPost(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>post(url), null, map == null ? "" : GsonInstance.instance().toJson(map), callBack);
    }

    public static  <T> void obPostJson(String url, String json, final HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>post(url), null, json == null ? "" : json, callBack);
    }

    public static  <T> void obPut(String url, Map<String, Object> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>put(url), null, map == null ? "" : GsonInstance.instance().toJson(map), callBack);
    }

    public static  <T> void obPutJson(String url, String json, final HttpCallBack<T> callBack) {
        dealRequest(OkGo.<String>put(url), null, json == null ? "" : json, callBack);
    }

    public static  <T> void obDelete(String url, Map<String, String> map, HttpCallBack<T> callBack) {
        dealRequest(OkGo.delete(url), map, null, callBack);
    }

    private static  <T> void dealRequest(Request request, Map<String, String> map, String json, HttpCallBack<T> callBack) {
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
        adapt.subscribe(new HttpObserver(callBack, request.getUrl()));
    }

    public static void addDisposable(String hashTag, Disposable disposable) {
        KLog.e(TAG, "addDisposable hashTag is : " + hashTag);
        if (compositeDisposableMap == null) {
            compositeDisposableMap = new ConcurrentHashMap<>();
        }
        CompositeDisposable compositeDisposable = compositeDisposableMap.get(hashTag);
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
        compositeDisposableMap.put(hashTag, compositeDisposable);
    }

    /**
     * 结束网络访问
     * @param context
     */
    public static void clearDisposable(Context context) {
        if (context == null || compositeDisposableMap == null){
            return;
        }
        String hashTag = context.hashCode() + "";
        KLog.e(TAG, "clearDisposable hashTag is : " + hashTag);
        CompositeDisposable compositeDisposable = compositeDisposableMap.get(hashTag);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
            compositeDisposableMap.remove(hashTag);
        }
    }
}
