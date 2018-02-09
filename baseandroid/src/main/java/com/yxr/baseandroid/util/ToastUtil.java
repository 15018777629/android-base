package com.yxr.baseandroid.util;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by 63062 on 2017/12/18.
 */

public class ToastUtil {
    private static long preTime;

    private ToastUtil() {
    }

    public static void toast(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        long millis = System.currentTimeMillis();
        if (millis - preTime >= 2000) {
            preTime = millis;
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
