package com.yxr.baseandroid.util;

import android.util.Log;

/**
 * 日志打印工具类
 * Created by 63062 on 2018/1/25.
 */

public class LogUtil {

    private static final String TAG = "LOG_UTIL";

    private static boolean logOpen = true;

    private LogUtil() {
    }

    public static void switchLog(boolean open) {
        logOpen = open;
    }

    public static void v(String log) {
        v(null, log);
    }

    public static void v(String tag, String log) {
        if (logOpen) {
            Log.v(tag == null ? TAG : tag, log);
        }
    }

    public static void i(String log) {
        i(null, log);
    }

    public static void i(String tag, String log) {
        if (logOpen) {
            Log.i(tag == null ? TAG : tag, log);
        }
    }

    public static void w(String log) {
        w(null, log);
    }

    public static void w(String tag, String log) {
        if (logOpen) {
            Log.w(tag == null ? TAG : tag, log);
        }
    }

    public static void e(String log) {
        e(null, log);
    }

    public static void e(String tag, String log) {
        if (logOpen) {
            Log.e(tag == null ? TAG : tag, log);
        }
    }

}
