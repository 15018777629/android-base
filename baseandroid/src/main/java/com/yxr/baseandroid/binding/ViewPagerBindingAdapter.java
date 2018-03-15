package com.yxr.baseandroid.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by 63062 on 2018/2/26.
 */

public class ViewPagerBindingAdapter {
    @BindingAdapter(value = {"fragmentPagerAdapter", "pagerAdapter"}, requireAll = false)
    public static void setVPAdapter(ViewPager viewPager, FragmentPagerAdapter fragmentPagerAdapter, PagerAdapter pagerAdapter) {
        if (fragmentPagerAdapter != null) {
            viewPager.setAdapter(fragmentPagerAdapter);
        } else if (pagerAdapter != null) {
            viewPager.setAdapter(pagerAdapter);
        }
    }

    @BindingAdapter(value = {"currItemPosition", "smoothScroll"}, requireAll = false)
    public static void setVPCurrItem(ViewPager viewPager, int position, boolean smoothScroll) {
        viewPager.setCurrentItem(position, smoothScroll);
    }

    @BindingAdapter(value = {"offscreenPageLimit"})
    public static void setOffscreenPageLimit(ViewPager viewPager, int offscreenPageLimit) {
        viewPager.setOffscreenPageLimit(offscreenPageLimit);
    }
}
