package com.yxr.baseandroid.base.ui;

import com.yxr.baseandroid.listener.SingleClickListener;
import com.yxr.baseandroid.ui.TitleBar;

/**
 * Created by 63062 on 2018/4/3.
 */

public interface ITitleBarUi {
    void setBaseTitleVisible(boolean visible);

    void setBaseTitle(String title);

    void setLeftListener(SingleClickListener listener);

    void setLeftVisible(boolean visible);

    void addRightActions(TitleBar.ActionList actionList);
}
