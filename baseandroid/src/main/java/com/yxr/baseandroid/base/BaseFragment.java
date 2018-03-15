package com.yxr.baseandroid.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxr.baseandroid.helper.StatusHelper;
import com.yxr.baseandroid.listener.SingleClickListener;
import com.yxr.baseandroid.util.ToastUtil;

/**
 * BaseFragment
 * Created by 63062 on 2017/12/18.
 */

public abstract class BaseFragment extends Fragment implements BaseUi{
    protected View rootView;
    protected BaseViewModel viewModel;
    private boolean uiPrepare;
    private boolean dataLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        firstInitData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, contentView(), null, false);
        if (binding == null || binding.getRoot() == null){
            throw new RuntimeException("binding.getRoot() is null!!!");
        }
        viewModel = initViewModel();
        if (viewModel != null) {
            StatusHelper statusHelper = new StatusHelper(getActivity());
            statusHelper.setNetErrorClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    viewModel.initBizData();
                }
            });
            viewModel.setStatusHelper(statusHelper);
            viewModel.setBinding(binding);
            viewModel.initListener();
        }
        uiPrepare = true;
        rootView = binding.getRoot();
        initView(savedInstanceState);
        initListener();
        if (viewModel != null){
            viewModel.initData();
        }
        firstInitData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rootView = null;
        if (viewModel != null){
            viewModel.onDestroy();
            viewModel = null;
        }
        uiPrepare = false;
        dataLoaded = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null){
            viewModel.onDestroy();
            viewModel = null;
        }
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void toast(@NonNull String toast) {
        ToastUtil.toast(getActivity(), toast);
    }

    public View findViewById(int id){
        return rootView.findViewById(id);
    }

    /**
     * 初始化数据
     * 当UI准备完成并且Fragment是可见的并且还没有初始化过数据
     */
    private void firstInitData() {
        if (uiPrepare && getUserVisibleHint() && !dataLoaded){
            dataLoaded = true;
            if (viewModel != null){
                viewModel.initBizData();
            }
        }
    }
}
