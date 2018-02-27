package com.wind.base.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2018/1/2.
 */

public abstract class BaseDelegateRecyclerAdapter extends RecyclerView.Adapter {

    protected AdapterDelegatesManager<List<DisplayItem>> manager;
    private List<DisplayItem> items;
    protected Activity mActivity;
    public BaseDelegateRecyclerAdapter(Activity activity){
        manager=new AdapterDelegatesManager<>();
        this.items=new ArrayList<>();
        this.mActivity=activity;
        addDelegate();
    }

    protected abstract void addDelegate();


    @Override
    public int getItemViewType(int position) {
        return manager.getItemViewType(items,position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return manager.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        manager.onBindViewHolder(items,position,holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<DisplayItem> getItems() {
        return items;
    }

    public void replace(List<DisplayItem> items) {
        this.items.clear();
        addAll(items);
    }

    public void addAll(List<DisplayItem> items){


        this.items.addAll(items);


        notifyDataSetChanged();
    }

    public DisplayItem getItem(int position){
        return items.get(position);
    }

}
