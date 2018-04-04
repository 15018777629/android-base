package com.yxr.base.mvc;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseFragment;

/**
 * MVC模式 可以这样使用BaseFragment
 * 注意点参考单行注释
 * Created by 63062 on 2018/4/4.
 */

// 可以无需给BaseFragment指定泛型
public class MVCFragment extends BaseFragment {
    private TextView tvContent;

    @Override
    public int contentView() {
        return R.layout.fragment_mvc;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // Fragment 懒加载机制，只有第一次可见的时候才会调用initData()
        // MVC模式可以在这个下面操作数据和业务
        initBizData();
    }

    @Override
    public BasePresenter initBasePresenter() {
        return null;
    }

    private void initBizData() {
        tvContent.setText("mvc loading...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvContent.setText("mvp load complete!!");
            }
        }, 2500);
    }

}
