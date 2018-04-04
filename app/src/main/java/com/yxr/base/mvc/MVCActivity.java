package com.yxr.base.mvc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yxr.base.R;
import com.yxr.base.adapter.FragmentAdapter;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MVC模式 可以这样使用BaseActivity
 * 注意点参考单行注释
 * Created by 63062 on 2018/4/4.
 */

// 可以无需给BaseActivity指定泛型
public class MVCActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public int contentView() {
        return R.layout.activity_mvc;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // MVC模式可以在这个下面操作数据和业务
        initBizData();
    }

    @Override
    public BasePresenter initBasePresenter() {
        // MVP,MVVM不能return null, MVC只需要return null即可
        return null;
    }

    private void initBizData() {
        fragmentList.add(new MVCFragment());
        fragmentList.add(new MVCFragment());
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));
    }

    public static void jumpHere(Context context){
        context.startActivity(new Intent(context, MVCActivity.class));
    }
}
