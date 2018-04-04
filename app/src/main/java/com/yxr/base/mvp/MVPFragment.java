package com.yxr.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yxr.base.R;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseFragment;

/**
 * MVP模式 可以这样使用MVPFragment
 * 注意点参考单行注释
 * Created by 63062 on 2018/4/4.
 */

// 指定Fragment泛型MVPFragment<BasePresenter>
public class MVPFragment extends BaseFragment<BasePresenter> implements IMVPFragmentView {
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
        // MVP 可以在这里进行无关业务，无需重复调用的数据初始化
    }

    @Override
    public BasePresenter initBasePresenter() {
        // MVP 在这里可以直接return BasePresenter的子类
        // 参数依次为 ：上下文 - IBaseView - HttpHelper(不需要访问网络可以直接传null)
        return new MVPFragmentPresenter(activity, this, null);
    }

    @Override
    public void loadStatus(int status) {
        tvContent.setText(0 == status ? "mvp loading..." : "mvp load complete!!");
    }
}
