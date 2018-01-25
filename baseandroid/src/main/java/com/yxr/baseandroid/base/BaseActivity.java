package com.yxr.baseandroid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.yxr.baseandroid.http.HttpHelper;
import com.yxr.baseandroid.manager.AppManager;
import com.yxr.baseandroid.util.ToastUtil;

import java.util.List;

/**
 * Created by 63062 on 2017/12/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseUi {
    protected HttpHelper httpHelper = new HttpHelper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(contentView());

        initView(savedInstanceState);
        initListener();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        if (httpHelper != null){
            httpHelper.clearDisposable();
            httpHelper = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null && manager.getFragments() != null){
            List<Fragment> fragments = manager.getFragments();
            for (Fragment fragment : fragments){
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void toast(@NonNull String toast) {
        ToastUtil.toast(this, toast);
    }
}
