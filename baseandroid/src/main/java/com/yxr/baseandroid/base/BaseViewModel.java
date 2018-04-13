package com.yxr.baseandroid.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

/**
 * Created by 63062 on 2018/4/4.
 */

public abstract class BaseViewModel extends BasePresenter {
    private ViewDataBinding binding;

    public BaseViewModel(@NonNull Context context) {
        super(context, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
        binding = null;
    }

    public void setBinding(ViewDataBinding binding){
        if (binding == null){
            throw new RuntimeException("binding must not be null");
        }
        this.binding = binding;
    }

    /**
     * @return ：获取ViewDataBinding
     */
    public <T extends ViewDataBinding> T getBinding() {
        if (binding == null){
            throw new RuntimeException("binding must not be null");
        }
        return (T) binding;
    }
}
