package com.yxr.base.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseFragment;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMFragment extends BaseFragment<BaseViewModel> {
    @Override
    public int contentView() {
        return R.layout.fragment_mvvm;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseViewModel initBasePresenter() {
        return new MVVMFragmentViewModel(activity, null);
    }

}
