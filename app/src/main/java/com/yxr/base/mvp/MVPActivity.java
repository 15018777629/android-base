package com.yxr.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yxr.base.R;
import com.yxr.base.adapter.FragmentAdapter;
import com.yxr.base.mvc.MVCFragment;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MVP模式 可以这样使用BaseActivity
 * 注意点参考单行注释
 * Created by 63062 on 2018/4/4.
 */

// 指定Activity泛型BaseActivity<BasePresenter>(fragment 同理)
public class MVPActivity extends BaseActivity<BasePresenter> implements IMVPActivityView {
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int contentView() {
        return R.layout.activity_mvc;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // MVP 可以在这里进行无关业务，无需重复调用的数据初始化
        fragmentList.add(new MVPFragment());
        fragmentList.add(new MVPFragment());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    public BasePresenter initBasePresenter() {
        // MVP 在这里可以直接return BasePresenter的子类
        // 参数依次为 ：上下文 - IBaseView
        return new MVPActivityPresenter(this, this);
    }

    @Override
    public void showToast(String toast) {
        toast(toast);
    }

    public static void jumpHere(Context context) {
        context.startActivity(new Intent(context, MVPActivity.class));
    }
}
