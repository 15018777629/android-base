package com.yxr.baseandroid.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by kelin on 16-4-26.
 */
public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = {"recyclerLayoutManager", "recyclerAdapter"}, requireAll = false)
    public static void adapterCommand(final RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter recyclerAdapter){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public static class ScrollDataWrapper {
        public float scrollX;
        public float scrollY;
        public int state;

        public ScrollDataWrapper(float scrollX, float scrollY, int state) {
            this.scrollX = scrollX;
            this.scrollY = scrollY;
            this.state = state;
        }
    }
}
