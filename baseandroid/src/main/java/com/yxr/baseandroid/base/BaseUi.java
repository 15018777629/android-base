package com.yxr.baseandroid.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

/**
 * Created by 63062 on 2017/12/1.
 */

public interface BaseUi {

    @LayoutRes
    int contentView();

    void initView(@Nullable Bundle savedInstanceState);

    void initListener();

    void initData();

    void toast(@NonNull String toast);
}
