package com.yxr.baseandroid.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Created by 63062 on 2017/12/1.
 */

public interface BaseUi {

    @LayoutRes
    int contentView();

    BaseViewModel initViewModel();

    void initView(@Nullable Bundle savedInstanceState);

    void initListener();

    void toast(String toast);
}
