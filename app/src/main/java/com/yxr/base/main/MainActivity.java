package com.yxr.base.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yxr.base.R;
import com.yxr.base.mvc.MVCActivity;
import com.yxr.base.mvp.MVPActivity;
import com.yxr.base.mvvm.MVVMActivity;
import com.yxr.base.status.MvcStatusActivity;
import com.yxr.base.status.mvvm.MvvmStatusActivity;
import com.yxr.baseandroid.base.BasePresenter;
import com.yxr.baseandroid.base.ui.BaseActivity;

/**
 * Created by 63062 on 2018/4/4.
 */

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String[] ITEMS = {"MVC模式下BaseActivity/BaseFragment使用"
            , "MVP模式下BaseActivity/BaseFragment使用"
            , "MVVM模式下BaseActivity/BaseFragment使用"
            , "Mvc BaseStatusActivity使用"
            , "Mvvm BaseStatusActivity使用"};

    private ListView listView;

    @Override
    public int contentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public BasePresenter initBasePresenter() {
        return null;
    }

    @Override
    public void initData() {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                MVCActivity.jumpHere(this);
                break;
            case 1:
                MVPActivity.jumpHere(this);
                break;
            case 2:
                MVVMActivity.jumpHere(this);
                break;
            case 3:
                MvcStatusActivity.jumpHere(this);
                break;
            case 4:
                MvvmStatusActivity.jumpHere(this);
                break;
        }
    }
}
