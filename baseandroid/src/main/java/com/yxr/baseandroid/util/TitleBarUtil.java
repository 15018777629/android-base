package com.yxr.baseandroid.util;

import android.view.View;

import com.yxr.baseandroid.listener.SingleClickListener;
import com.yxr.baseandroid.ui.TitleBar;

/**
 * Created by 63062 on 2018/4/4.
 */

public class TitleBarUtil {

    public static void setTitleVisible(TitleBar titleBar, boolean visible) {
        if (titleBar != null) {
            titleBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public static void setTitle(TitleBar titleBar, String title) {
        if (titleBar != null) {
            titleBar.setTitle(title);
        }
    }

    public static void setLeftListener(TitleBar titleBar, SingleClickListener listener) {
        if (titleBar != null) {
            titleBar.setLeftClickListener(listener);
        }
    }

    public static void setLeftVisible(TitleBar titleBar, boolean visible) {
        if (titleBar != null) {
            titleBar.setLeftVisible(visible);
        }
    }

    public static void addRightActions(TitleBar titleBar, TitleBar.ActionList actionList) {
        if (titleBar != null) {
            titleBar.addActions(actionList);
        }
    }
}
