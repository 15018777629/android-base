package com.yxr.baseandroid.listener;

import android.view.View;

/**
 * Created by 63062 on 2018/3/1.
 */

public abstract class SingleClickListener implements View.OnClickListener {

    private long preTime = 0;

    @Override
    public final void onClick(View view) {
        long millis = System.currentTimeMillis();
        if (millis - preTime > 1000) {
            preTime = millis;
            onSingleClick(view);
        }
    }

    public abstract void onSingleClick(View view);
}
