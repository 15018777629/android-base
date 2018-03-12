package com.yxr.baseandroid.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by 63062 on 2017/10/21.
 */

public class GlideUtil {
    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .skipMemoryCache(true)
            .placeholder(com.yxr.baseandroid.R.drawable.replace)
            .error(com.yxr.baseandroid.R.drawable.error);

    public static RequestOptions optionsNoCrop = new RequestOptions()
            .dontAnimate()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(com.yxr.baseandroid.R.drawable.replace)
            .error(com.yxr.baseandroid.R.drawable.error);

    public static void display(Context context, Object uri, ImageView imageView) {
        display(context, uri, imageView, null);
    }

    public static void displayNoCrop(Context context, Object uri, ImageView imageView) {
        display(context, uri, imageView, optionsNoCrop);
    }

    public static void display(Context context, Object uri, ImageView imageView, RequestOptions requestOptions) {
        if (canDisplay(context, imageView)) {
            Glide.with(context)
                    .load(uri)
                    .apply(requestOptions == null ? options : requestOptions)
                    .into(imageView);
        }
    }

    private static boolean canDisplay(Context context, ImageView imageView) {
        if (imageView == null || context == null) {
            return false;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return false;
        }
        return true;
    }
}
