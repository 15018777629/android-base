package com.yxr.base.status.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.yxr.base.databinding.ActivityMvvmStatusBinding;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseStatusActivity;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MvvmStatusViewModel extends BaseViewModel {
    public final ObservableField<String> status = new ObservableField<>("loading");
    private ActivityMvvmStatusBinding binding;
    private BaseStatusActivity activity;

    public MvvmStatusViewModel(@NonNull Context context) {
        super(context);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        binding = getBinding();
        binding.setStatusViewModel(this);
        activity = getContext();
    }

    @Override
    public void initBizData() {
        iStatus.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                status.set("init biz data complete!!");
                iStatus.dismissLoading();
            }
        },2500);
    }
}
