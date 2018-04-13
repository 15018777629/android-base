package com.yxr.baseandroid.base;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 63062 on 2018/4/3.
 */

public class BaseApplication extends Application {
    private static final boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();

        initKLog();
        initOkGo();
    }

    private void initKLog() {
        KLog.init(DEBUG);
    }

    private void initOkGo() {

    }
}
