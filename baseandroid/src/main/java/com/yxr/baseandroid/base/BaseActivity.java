package com.yxr.baseandroid.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yxr.baseandroid.helper.StatusHelper;
import com.yxr.baseandroid.listener.SingleClickListener;
import com.yxr.baseandroid.manager.AppManager;
import com.yxr.baseandroid.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * BaseActivity
 * Created by 63062 on 2017/12/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseUi {
    protected BaseViewModel viewModel;
    private WeakReference<Activity> activityWeakReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWeakReference = new WeakReference<Activity>(this);
        AppManager.getAppManager().addActivity(activityWeakReference);

        ViewDataBinding binding = DataBindingUtil.setContentView(this, contentView());
        viewModel = initViewModel();

        initView(savedInstanceState);
        initListener();

        if (viewModel != null) {
            StatusHelper statusHelper = new StatusHelper(this);
            statusHelper.setNetErrorClickListener(new SingleClickListener() {
                @Override
                public void onSingleClick(View view) {
                    viewModel.initBizData();
                }
            });
            viewModel.setBinding(binding);
            viewModel.setStatusHelper(statusHelper);
            viewModel.initListener();
            viewModel.initData();
            viewModel.initBizData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(activityWeakReference);
        if (viewModel != null) {
            viewModel.onDestroy();
            viewModel = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null && manager.getFragments() != null) {
            List<Fragment> fragments = manager.getFragments();
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
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
        ToastUtil.toast(this, toast);
    }

    public WeakReference<Activity> getActivityWeakReference() {
        return activityWeakReference;
    }
}
