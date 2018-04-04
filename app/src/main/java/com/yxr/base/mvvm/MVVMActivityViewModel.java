package com.yxr.base.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.yxr.base.adapter.FragmentAdapter;
import com.yxr.base.databinding.ActivityMvvmBinding;
import com.yxr.base.mvc.MVCFragment;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseActivity;
import com.yxr.baseandroid.http.HttpHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMActivityViewModel extends BaseViewModel {
    public final ObservableField<FragmentAdapter> fragmentAdapter = new ObservableField<>();
    public final ObservableInt offLimit = new ObservableInt();
    private ActivityMvvmBinding binding;

    public MVVMActivityViewModel(@NonNull Context context, HttpHelper httpHelper) {
        super(context, httpHelper);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        binding = getBinding();
        binding.setMvvmViewModel(this);

        BaseActivity activity = (BaseActivity) context;
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new MVVMFragment());
        fragmentList.add(new MVVMFragment());

        fragmentAdapter.set(new FragmentAdapter(activity.getSupportFragmentManager(), fragmentList));
        offLimit.set(fragmentList.size());
    }

    @Override
    public void initBizData() {
        // 模拟获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((BaseActivity) context).toast("mvvm init biz data complete!");
            }
        },2500);
    }
}
