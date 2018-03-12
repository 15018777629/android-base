package com.yxr.baseandroid.helper;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yxr.baseandroid.R;
import com.yxr.baseandroid.http.HttpCode;
import com.yxr.baseandroid.listener.SingleClickListener;

/**
 * 状态帮助
 * Created by 63062 on 2018/3/8.
 */

public class StatusHelper {

    private int loadingLayout;
    private int emptyLayout;
    private int newErrorLayout;
    private View loadingView;
    private View emptyView;
    private View netErrorView;
    private ViewGroup decorView;
    private ViewGroup.LayoutParams layoutParams;
    private SingleClickListener netErrorClickListener;

    public StatusHelper(Activity activity) {
        setParentView(activity);
    }

    public StatusHelper(ViewGroup decorView) {
        setRootView(decorView);
    }

    public StatusHelper(Activity activity, int loadingLayout) {
        setParentView(activity);
        this.loadingLayout = loadingLayout;
    }

    public StatusHelper(Activity activity, int loadingLayout, int emptyLayout) {
        setParentView(activity);
        this.loadingLayout = loadingLayout;
        this.emptyLayout = emptyLayout;
    }

    public StatusHelper(Activity activity, @LayoutRes int loadingLayout
            , @LayoutRes int emptyLayout, @LayoutRes int newErrorLayout) {
        setParentView(activity);
        this.loadingLayout = loadingLayout;
        this.emptyLayout = emptyLayout;
        this.newErrorLayout = newErrorLayout;
    }

    private void setParentView(Activity activity) {
        if (activity == null || activity.isDestroyed() || activity.getWindow() == null){
            return;
        }
        setRootView((ViewGroup) activity.getWindow().getDecorView());
    }

    public void setRootView(ViewGroup decorView) {
        this.decorView = decorView;
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams){
        this.layoutParams = layoutParams;
    }

    public void setNetErrorClickListener(SingleClickListener netErrorClickListener){
        this.netErrorClickListener = netErrorClickListener;
    }

    public void loading() {
        dismiss();
        loadingView = addView(loadingView, loadingLayout, R.layout.status_default_loading);
    }

    public void exception(int code) {
        if (code == HttpCode.EXCEPTION_NO_CONNECT || code == HttpCode.EXCEPTION_TIME_OUT) {
            netError();
        } else {
            empty();
        }
    }

    public void empty() {
        dismiss();
        emptyView = addView(emptyView, emptyLayout, R.layout.status_default_empty);
    }

    public void netError() {
        dismiss();
        netErrorView = addView(netErrorView, newErrorLayout, R.layout.status_default_no_network);
        if (netErrorView != null){
            decorView.setClickable(false);
            netErrorView.setOnClickListener(netErrorClickListener);
        }
    }

    public void dismiss() {
        if (decorView == null) {
            return;
        }
        decorView.setClickable(false);
        if (loadingView != null) {
            decorView.removeView(loadingView);
            loadingView = null;
        }
        if (emptyView != null) {
            decorView.removeView(emptyView);
            emptyView = null;
        }
        if (netErrorView != null) {
            decorView.removeView(netErrorView);
            netErrorView = null;
        }
    }

    private View addView(View view, int layoutId, int defaultLayout) {
        if (decorView == null) {
            return view;
        }
        decorView.setClickable(true);
        if (view == null) {
            view = LayoutInflater.from(decorView.getContext()).inflate(layoutId <= 0 ? defaultLayout : layoutId, decorView, false);
            if (layoutParams != null){
              decorView.setLayoutParams(layoutParams);
            } else if (decorView instanceof FrameLayout){
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.gravity = Gravity.CENTER;
                view.setLayoutParams(layoutParams);
            } else if (decorView instanceof LinearLayout){
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.gravity = Gravity.CENTER;
                view.setLayoutParams(layoutParams);
            } else if (decorView instanceof RelativeLayout){
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                view.setLayoutParams(layoutParams);
            }
        }
        decorView.addView(view);
        return view;
    }

}
