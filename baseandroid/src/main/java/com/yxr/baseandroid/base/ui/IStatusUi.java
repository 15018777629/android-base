package com.yxr.baseandroid.base.ui;

/**
 * Created by 63062 on 2018/4/3.
 */

public interface IStatusUi {

    void showLoading();

    void dismissLoading();

    void dismissLoading(boolean showEmpty, int httpCode);

    void showEmpty();
}
