package com.yxr.baseandroid.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yxr.baseandroid.R;
import com.yxr.baseandroid.util.GlideUtil;

/**
 * Created by kelin on 16-3-24.
 */
public final class ImageViewBindingAdapter {
    private static RequestOptions options = new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .skipMemoryCache(true);

    private static RequestOptions optionsNoCrop = new RequestOptions()
            .dontAnimate()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE);

    @BindingAdapter(value = {"imageUrl", "noCrop", "placeHolder", "errorHolder"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String imageUrl, boolean noCrop
            , @DrawableRes int placeHolder, @DrawableRes int errorHolder) {
        initOptions(noCrop, placeHolder, errorHolder);
        loadImage(imageView, imageUrl, options);
    }

    @BindingAdapter(value = {"imageRes", "noCrop", "placeHolder", "errorHolder"}, requireAll = false)
    public static void setImageRes(ImageView imageView, @DrawableRes int imageRes, boolean noCrop
            , @DrawableRes int placeHolder, @DrawableRes int errorHolder) {
        initOptions(noCrop, placeHolder, errorHolder);
        loadImage(imageView, imageRes, noCrop ? optionsNoCrop : options);
    }

    private static void initOptions(boolean noCrop, int placeHolder, int errorHolder) {
        if (noCrop) {
            optionsNoCrop.placeholder(placeHolder == 0 ? GlideUtil.getRandomRes() : placeHolder);
            optionsNoCrop.error(errorHolder == 0 ? GlideUtil.getRandomRes() : errorHolder);
        } else {
            options.placeholder(placeHolder == 0 ? GlideUtil.getRandomRes() : placeHolder);
            options.error(errorHolder == 0 ? GlideUtil.getRandomRes() : errorHolder);
        }
    }

    private static void loadImage(ImageView imageView, Object object, RequestOptions opt) {
        if (imageView == null || opt == null) {
            return;
        }
        GlideUtil.display(imageView.getContext(), object, imageView, opt);
    }
}

