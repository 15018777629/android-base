package com.yxr.baseandroid.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.TextView;

import com.yxr.baseandroid.ui.TitleBar;

import java.util.List;

/**
 * Created by 63062 on 2018/3/12.
 */

public class TitleBarBindingAdapter {

    @BindingAdapter(value = {"leftImageResource"})
    public static void setLeftImageResource(TitleBar titleBar, @DrawableRes int leftImageResource) {
        titleBar.setLeftImageResource(leftImageResource);
    }

    @BindingAdapter(value = {"leftClickListener"})
    public static void setLeftClickListener(TitleBar titleBar, View.OnClickListener leftClickListener) {
        titleBar.setLeftClickListener(leftClickListener);
    }

    @BindingAdapter(value = {"leftVisible"})
    public static void setLeftVisible(TitleBar titleBar, boolean leftVisible) {
        titleBar.setLeftVisible(leftVisible);
    }

    @BindingAdapter(value = {"title"})
    public static void setTitle(TitleBar titleBar, String title) {
        titleBar.setTitle(title);
    }

    @BindingAdapter(value = {"titleColor"})
    public static void setTitleColor(TitleBar titleBar, int titleColor) {
        titleBar.setTitleColor(titleColor);
    }

    @BindingAdapter(value = {"textAction", "actionTextColor"})
    public static void setActionTextColor(TitleBar titleBar, TitleBar.TextAction textAction, int actionTextColor) {
        if (textAction != null){
            View view = titleBar.findViewWithTag(textAction);
            if (view != null && view instanceof TextView){
                ((TextView) view).setTextColor(actionTextColor);
            }
        }
    }

    @BindingAdapter(value = {"action"})
    public static void addAction(TitleBar titleBar, TitleBar.Action action) {
        if (action != null) {
            titleBar.addAction(action);
        }
    }

    @BindingAdapter(value = {"actions"})
    public static void addActions(TitleBar titleBar, TitleBar.ActionList actions) {
        if (actions != null && !actions.isEmpty()) {
            titleBar.addActions(actions);
        }
    }
}
