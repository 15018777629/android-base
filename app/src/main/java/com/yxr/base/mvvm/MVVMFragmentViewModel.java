package com.yxr.base.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.yxr.base.databinding.FragmentMvvmBinding;
import com.yxr.baseandroid.base.BaseViewModel;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMFragmentViewModel extends BaseViewModel {
    public final ObservableField<String> status = new ObservableField<>("lazy fragment");
    private FragmentMvvmBinding binding;
    public MVVMFragmentViewModel(@NonNull Context context) {
        super(context);
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
