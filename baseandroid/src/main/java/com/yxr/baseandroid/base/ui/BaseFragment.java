package com.yxr.baseandroid.base.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.util.ToastUtil;

/**
 * BaseFragment
 * Created by 63062 on 2017/12/18.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseUi <T> {
    protected Activity activity;
    protected View rootView;
    /**
     * UI是否准备完成，用于数据懒加载
     */
    protected boolean uiPrepare;
    /**
     * 数据是否加载过了，用于数据懒加载
     */
    protected boolean dataLoaded;
    public T basePresenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 用户可见不可见，用于Fragment数据懒加载
        firstInitData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        rootView = inflater.inflate(contentView(), null);
        uiPrepare = true;

        // 初始化View
        initView(savedInstanceState);
        // 初始化事件监听
        initListener();
        // 初始化业务基类
        basePresenter = initBasePresenter();
        if (basePresenter != null) {
            if (basePresenter instanceof BaseViewModel) {
                // 如果是DataBinding类型
                ViewDataBinding binding = DataBindingUtil.bind(rootView);
                ((BaseViewModel) basePresenter).setBinding(binding);
            }
            basePresenter.initListener();
            basePresenter.initData();
        }

        firstInitData();
        return titleAndStatus() ? initStatusView(rootView) : rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroy();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    /**
     * 重新加载数据(一般用于网络)
     */
    @Override
    public void reloadData(){
        if (basePresenter == null){
            initData();
        } else {
            basePresenter.initBizData();
        }
    }

    @Override
    public void toast(@NonNull String toast) {
        ToastUtil.toast(getActivity(), toast);
    }

    public View findViewById(int id) {
        return rootView.findViewById(id);
    }

    public boolean titleAndStatus() {
        return false;
    }

    public View initStatusView(View rootView) {
        return null;
    }

    /**
     * 初始化数据
     * 当UI准备完成并且Fragment是可见的并且还没有初始化过数据
     */
    public void firstInitData() {
        if (uiPrepare && getUserVisibleHint() && !dataLoaded) {
            dataLoaded = true;
            initData();
            if (basePresenter != null) {
                basePresenter.initBizData();
            }
        }
    }

    public void destroy() {
        rootView = null;
        uiPrepare = false;
        dataLoaded = false;
    }

}
