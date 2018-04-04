package com.yxr.baseandroid.util;

import android.support.annotation.LayoutRes;
import android.widget.RelativeLayout;

import com.classic.common.MultipleStatusView;
import com.yxr.baseandroid.http.HttpCode;

/**
 * Created by 63062 on 2018/4/3.
 */

public class StatusUtil {
    private static final RelativeLayout.LayoutParams DEFAULT_LAYOUT_PARAMS =
            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);

    /**
     * 切换为loading状态
     */
    public static void showLoading(MultipleStatusView multipleStatusView) {
        try {
            if (multipleStatusView != null)
                multipleStatusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏loading状态，并展示内容布局
     */
    public static void dismissLoading(MultipleStatusView multipleStatusView) {
        showContent(multipleStatusView);
    }

    public static void dismissLoading(MultipleStatusView multipleStatusView, boolean showEmpty, int code) {
        if (HttpCode.EXCEPTION_NO_CONNECT == code || HttpCode.EXCEPTION_TIME_OUT == code)
            showNoNetwork(multipleStatusView, 0);
        else if (showEmpty) {
            showEmpty(multipleStatusView);
        } else {
            showContent(multipleStatusView);
        }
    }

    /**
     * 展示内容布局
     */
    private static void showContent(MultipleStatusView multipleStatusView) {
        try {
            if (multipleStatusView != null)
                multipleStatusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 展示空数据布局
     */
    public static void showEmpty(MultipleStatusView multipleStatusView) {
        try {
            if (multipleStatusView != null)
                multipleStatusView.showEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示网络异常布局
     */
    private static void showNoNetwork(MultipleStatusView multipleStatusView, @LayoutRes int noNetWorkLayoutId) {
        try {
            if (multipleStatusView != null) {
                if (noNetWorkLayoutId == 0) {
                    multipleStatusView.showNoNetwork();
                } else {
                    multipleStatusView.showNoNetwork(noNetWorkLayoutId, DEFAULT_LAYOUT_PARAMS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
