package com.yxr.base;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxr.base.databinding.ItemMainBinding;

import java.util.List;

/**
 * Created by 63062 on 2018/2/23.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private List<User> userList;

    public MainAdapter(List<User> userList){
        this.userList = userList;
    }

    @Override
    public MainAdapter.MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main, parent, false);
        return new MainHolder(binding);
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainHolder holder, int position) {
        holder.bindData(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class MainHolder extends RecyclerView.ViewHolder{
        private final ItemMainBinding binding;
        public MainHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(final User u){
            binding.setUser(u);
            binding.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    u.age.set(u.age.get() + 1);
                }
            });
        }
    }
}
