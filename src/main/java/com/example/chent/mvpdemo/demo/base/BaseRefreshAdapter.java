package com.example.chent.mvpdemo.demo.base;

import android.content.Context;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chent on 2017/9/25.
 */

public abstract class BaseRefreshAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseRefreshAdapter(Context context, List<T> data) {
        super(data);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = data;
    }

    public Context getContext() {
        return context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public List<T> getList() {
        return list;
    }

    public void refreshAdapter(boolean isDownRefresh, List<T> list){
        if (isDownRefresh){
            getList().clear();
        }
        if (list != null){
            getList().addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(K helper, T item) {

    }
}
