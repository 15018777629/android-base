package com.yxr.base.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.yxr.base.adapter.FragmentAdapter;
import com.yxr.base.databinding.ActivityMvvmBinding;
import com.yxr.base.databinding.FragmentMvvmBinding;
import com.yxr.base.mvc.MVCFragment;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseActivity;
import com.yxr.baseandroid.http.HttpHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMFragmentViewModel extends BaseViewModel {
    public final ObservableField<String> status = new ObservableField<>("lazy fragment");
    private FragmentMvvmBinding binding;
    public MVVMFragmentViewModel(@NonNull Context context, HttpHelper httpHelper) {
        super(context, httpHelper);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        binding = getBinding();
        binding.setMvvmViewModel(this);
    }

    @Override
    public void initBizData() {
        status.set("mvvm loading...");
        // 模拟获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                status.set("mvvm load complete!!");
            }
        },2500);
    }
}
