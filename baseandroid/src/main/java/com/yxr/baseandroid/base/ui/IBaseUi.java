package com.yxr.baseandroid.base.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.yxr.baseandroid.base.BasePresenter;

/**
 * Created by 63062 on 2017/12/1.
 */

public interface IBaseUi<T extends BasePresenter> {

    @LayoutRes
    int contentView();

    void initView(@Nullable Bundle savedInstanceState);

    void initListener();

    T initBasePresenter();

    void initStatusInterface();

    void initData();

    void reloadData();

    void toast(String toast);
}
