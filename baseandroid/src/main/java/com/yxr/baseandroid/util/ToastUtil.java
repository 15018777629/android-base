package com.yxr.baseandroid.util;


import android.content.Context;
import android.widget.Toast;

/**
 * Toast 控制器
 * Created by 63062 on 2017/12/18.
 */

public class ToastUtil {
    private static long preTime;

    private ToastUtil() {
    }

    public static void toast(Context context, String msg) {
        long millis = System.currentTimeMillis();
        if (millis - preTime >= 2000) {
            preTime = millis;
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
