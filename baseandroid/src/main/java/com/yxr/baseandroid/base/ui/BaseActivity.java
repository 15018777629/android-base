package com.yxr.baseandroid.base.ui;

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
import android.view.LayoutInflater;
import android.view.View;

import com.yxr.baseandroid.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.manager.AppManager;
import com.yxr.baseandroid.util.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Activity基类
 * Created by 63062 on 2018/4/2.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseUi<T>{
    private WeakReference<Activity> activityWeakReference;
    protected T basePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 软引用Activity上下文
        activityWeakReference = new WeakReference<Activity>(this);
        // 添加到App管理中
        AppManager.getAppManager().addActivity(activityWeakReference);

        // 初始化布局
        View contentView = LayoutInflater.from(this).inflate(contentView(), null);
        if (titleAndStatus()){
            setContentView(R.layout.activity_base_status);
            initStatusView(contentView);
        } else {
            setContentView(contentView);
        }

        // 初始化View
        initView(savedInstanceState);
        // 初始化事件监听
        initListener();

        initData();

        // 初始化业务基类
        basePresenter = initBasePresenter();

        initStatusInterface();

        if (basePresenter != null) {
            if (basePresenter instanceof BaseViewModel) {
                // 如果是DataBinding类型
                ViewDataBinding binding = DataBindingUtil.bind(contentView);
                ((BaseViewModel) basePresenter).setBinding(binding);
            }
            // 业务初始化监听(EventBus之类的)
            basePresenter.initListener();
            // 业务初始化数据(与业务无关的数据)
            basePresenter.initData();
            // 业务初始化
            basePresenter.initBizData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (basePresenter != null) {
            basePresenter.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (basePresenter != null) {
            basePresenter.onStart();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (basePresenter != null) {
            basePresenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (basePresenter != null) {
            basePresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从App管理中移除
        AppManager.getAppManager().removeActivity(activityWeakReference);
        if (basePresenter != null) {
            basePresenter.onDestroy();
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
        if (basePresenter != null) {
            basePresenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void initStatusInterface() {}
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
        ToastUtil.toast(this, toast);
    }

    public boolean titleAndStatus() {
        return false;
    }

    public void initStatusView(View contentView) {}

    /**
     * @return ：获取当前Activity软引用
     */
    public WeakReference<Activity> getActivityWeakReference() {
        return activityWeakReference;
    }

    /**
     * @return ：获取业务基类
     */
    public BasePresenter getBasePresenter() {
        return basePresenter;
    }
}
