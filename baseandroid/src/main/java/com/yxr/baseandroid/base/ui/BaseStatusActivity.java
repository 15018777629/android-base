package com.yxr.baseandroid.base.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.classic.common.MultipleStatusView;
import com.yxr.baseandroid.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.listener.SingleClickListener;
import com.yxr.baseandroid.ui.TitleBar;
import com.yxr.baseandroid.util.StatusUtil;
import com.yxr.baseandroid.util.TitleBarUtil;

/**
 * 有多个状态的Activity基类
 * Created by 63062 on 2018/4/4.
 */

public abstract class BaseStatusActivity<T extends BasePresenter> extends BaseActivity<T> implements ITitleBarUi, IStatusUi {
    protected TitleBar tbBaseTitle;
    protected MultipleStatusView msvBaseStatusView;

    @Override
    public final boolean titleAndStatus() {
        return true;
    }

    @Override
    public void initStatusView(View contentView) {
        // 初始化多状态布局
        tbBaseTitle = (TitleBar) findViewById(R.id.tbBaseTitle);
        msvBaseStatusView = (MultipleStatusView) findViewById(R.id.msvBaseStatusView);
        msvBaseStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadData();
            }
        });

        FrameLayout flBaseContent = (FrameLayout) findViewById(R.id.flBaseContent);
        flBaseContent.addView(contentView);
    }

    @Override
    public void initStatusInterface() {
        if (basePresenter != null){
            basePresenter.setTitleAndStatusInt(this, this);
        }
    }

    @Override
    public void setBaseTitleVisible(boolean visible) {
        TitleBarUtil.setTitleVisible(tbBaseTitle, visible);
    }

    @Override
    public void setBaseTitle(String title) {
        TitleBarUtil.setTitle(tbBaseTitle, title);
    }

    @Override
    public void setLeftListener(SingleClickListener listener) {
        TitleBarUtil.setLeftListener(tbBaseTitle, listener);
    }

    @Override
    public void setLeftVisible(boolean visible) {
        TitleBarUtil.setLeftVisible(tbBaseTitle, visible);
    }

    @Override
    public void addRightActions(TitleBar.ActionList actionList) {
        TitleBarUtil.addRightActions(tbBaseTitle, actionList);
    }

    @Override
    public void showLoading() {
        StatusUtil.showLoading(msvBaseStatusView);
    }

    @Override
    public void dismissLoading() {
        StatusUtil.dismissLoading(msvBaseStatusView);
    }

    @Override
    public void dismissLoading(boolean showEmpty, int httpCode) {
        StatusUtil.dismissLoading(msvBaseStatusView, showEmpty, httpCode);
    }

    @Override
    public void showEmpty() {
        StatusUtil.showEmpty(msvBaseStatusView);
    }
}
