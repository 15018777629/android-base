package com.yxr.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yxr.base.databinding.ActivityMainBinding;
import com.yxr.baseandroid.base.BaseViewModel;
import com.yxr.baseandroid.http.HttpHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 63062 on 2018/2/22.
 */

public class MainViewModel extends BaseViewModel {
    public final User user = new User();
    public final MainAdapter adapter = new MainAdapter(null);
    public RecyclerView.LayoutManager layoutManager;
    private ActivityMainBinding binding;

    public MainViewModel(@NonNull Context context, HttpHelper httpHelper) {
        super(context, httpHelper);
        layoutManager = new LinearLayoutManager(context);
    }

    @Override
    public void initListener() {
        binding = getBinding();
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.age.set(user.age.get() + 1);
                user.avatar.set(user.age.get() % 2 == 0
                        ? "http://f.hiphotos.baidu.com/image/pic/item/503d269759ee3d6db032f61b48166d224e4ade6e.jpg"
                        : "http://img0.imgtn.bdimg.com/it/u=3565185884,2248353566&fm=27&gp=0.jpg");
            }
        });
    }

    @Override
    public void initData() {
        user.name.set("小玉米");
        user.age.set(18);
        user.avatar.set("http://img0.imgtn.bdimg.com/it/u=3565185884,2248353566&fm=27&gp=0.jpg");

        binding.setMain(this);
        binding.setUser(user);

        List<User> users = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            User u = new User();
            u.name.set("ITEM : " + i);
            u.age.set(i);
            u.avatar.set(u.age.get() % 2 == 0
                    ? "http://f.hiphotos.baidu.com/image/pic/item/503d269759ee3d6db032f61b48166d224e4ade6e.jpg"
                    : "http://img0.imgtn.bdimg.com/it/u=3565185884,2248353566&fm=27&gp=0.jpg");
            users.add(u);
        }
        adapter.setData(users);
    }
}
