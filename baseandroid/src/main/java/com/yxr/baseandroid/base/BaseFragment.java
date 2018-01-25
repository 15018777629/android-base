package com.yxr.baseandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxr.baseandroid.util.ToastUtil;

/**
 * Created by 63062 on 2017/12/18.
 */

public abstract class BaseFragment extends Fragment implements BaseUi{
    protected View rootView;
    private boolean uiPrepare;
    private boolean dataLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        firstInitData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), contentView(), null);

        initView(savedInstanceState);
        initListener();
        uiPrepare = true;
        firstInitData();

        return rootView;
    }

    @Override
    public void toast(@NonNull String toast) {
        ToastUtil.toast(getActivity(), toast);
    }

    public View findViewById(int id){
        return rootView.findViewById(id);
    }

    private void firstInitData() {
        if (uiPrepare && getUserVisibleHint() && !dataLoaded){
            dataLoaded = true;
            initData();
        }
    }
}
