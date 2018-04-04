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

    /**
     * 加载图片(一般用于网络图片)，默认缓存到本地
     * @param context ：上下文
     * @param uri ：图片地址
     * @param imageView ：加载控件
     */
    public static void display(Context context, Object uri, ImageView imageView) {
        display(context, uri, imageView, false);
    }

    /**
     * 加载图片(一般用于网络图片)
     * @param context ：上下文
     * @param uri ：图片地址
     * @param imageView ：加载控件
     * @param skipDiskCache ：是否需要缓存在本地
     */
    public static void display(Context context, Object uri, ImageView imageView, boolean skipDiskCache) {
        display(context, uri, imageView, createOption(skipDiskCache));
    }

    /**
     * 加载图片(一般用于本地图片)，默认跳过内存
     * @param context ：上下文
     * @param uri ：图片地址
     * @param imageView ：加载控件
     */
    public static void displayLocal(Context context, Object uri, ImageView imageView) {
        displayLocal(context, uri, imageView, true);
    }

    /**
     * 加载图片(一般用于本地图片)
     * @param context ：上下文
     * @param uri ：图片地址
     * @param imageView ：加载控件
     * @param skipMemoryCache ：是否跳过内存
     */
    public static void displayLocal(Context context, Object uri, ImageView imageView, boolean skipMemoryCache) {
        display(context, uri, imageView, createLocalOption(skipMemoryCache));
    }

    /**
     * 加载图片
     * @param context ：上下文
     * @param uri ：图片地址
     * @param imageView ：加载控件
     * @param requestOptions ：请求配置
     */
    public static void display(Context context, Object uri, ImageView imageView, RequestOptions requestOptions) {
        if (canDisplay(context, imageView)) {
            Glide.with(context)
                    .load(uri)
                    .apply(requestOptions == null ? createOption(false) : requestOptions)
                    .into(imageView);
        }
    }

    /**
     * @return ：随机获取图片
     */
    public static int getRandomRes() {
        return RES[new Random().nextInt(RES.length)];
    }

    /**
     * @param context ：上下文
     * @param imageView ：图片控件
     * @return ：是否满足加载图片的条件
     */
    private static boolean canDisplay(Context context, ImageView imageView) {
        if (imageView == null || context == null) {
            return false;
        }
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return false;
        }
        return true;
    }

    private static RequestOptions createOption(boolean skipDiskCache) {
        int randomRes = getRandomRes();
        return new RequestOptions()
                .centerCrop()
                .dontAnimate()
                .placeholder(randomRes)
                .error(randomRes)
                .skipMemoryCache(true)
                .diskCacheStrategy(skipDiskCache ? DiskCacheStrategy.NONE : DiskCacheStrategy.RESOURCE);
    }

    private static RequestOptions createLocalOption(boolean skipMemoryCache) {
        return new RequestOptions()
                .dontAnimate()
                .skipMemoryCache(skipMemoryCache);
    }
}
