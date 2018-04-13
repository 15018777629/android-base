package com.yxr.baseandroid.base;

import android.app.Application;

import com.socks.library.KLog;

/**
 * Created by 63062 on 2018/4/3.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initKLog();
    }

    private void initKLog() {
        KLog.init(BaseConfig.DEBUG);
    }

}
