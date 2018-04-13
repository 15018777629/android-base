package com.yxr.base.mvvm;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.yxr.base.adapter.FragmentAdapter;
import com.yxr.base.databinding.ActivityMvvmBinding;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.base.ui.BaseActivity;
import com.yxr.baseandroid.http.HttpCallBack;
import com.yxr.baseandroid.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MVVMActivityViewModel extends BaseViewModel {
    public final ObservableField<FragmentAdapter> fragmentAdapter = new ObservableField<>();
    public final ObservableInt offLimit = new ObservableInt();
    private ActivityMvvmBinding binding;

    public MVVMActivityViewModel(@NonNull Context context) {
        super(context);
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
        HttpUtil.obGet("http://op.juhe.cn/onebox/football/league?key=bbdf40a269d0f08936ddb07b076be559&league=%E6%B3%95%E7%94%B2"
                , null, new HttpCallBack<String>(context) {
                    @Override
                    public void onSuccess(String s) {
                        toast("mvvm init biz data complete!");
                    }

                    @Override
                    public void onError(int code, String msg) {
                        toast("mvvm init biz data complete!");
                    }
                });
    }
}
