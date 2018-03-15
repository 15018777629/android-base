package com.yxr.baseandroid.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yxr.baseandroid.R;

import java.util.Random;

/**
 * Created by 63062 on 2017/10/21.
 */

public class GlideUtil {
    private static final int[] RES = {R.drawable.default_dark_green, R.drawable.default_gray
            , R.drawable.default_light_green, R.drawable.default_pink};
    private static final Random random = new Random();

    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .skipMemoryCache(true);

    public static RequestOptions optionsNoCrop = new RequestOptions()
            .dontAnimate()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    public static void display(Context context, Object uri, ImageView imageView) {
        int res = getRandomRes();
        options.placeholder(res);
        options.error(res);
        display(context, uri, imageView, options);
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

    public static int getRandomRes() {
        return RES[random.nextInt(RES.length)];
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
