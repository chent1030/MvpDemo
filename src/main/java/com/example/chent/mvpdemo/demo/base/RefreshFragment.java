package com.example.chent.mvpdemo.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chent.mvpdemo.R;
import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.support.refresh.RefreshView;
import com.example.chent.mvpdemo.framework.support.refresh.fragment.BaseRefreshFragment;

/**
 * Created by chent on 2017/9/25.
 */

public abstract class RefreshFragment<D, V extends RefreshView<D>, P extends MvpPresenter<V>> extends
        BaseRefreshFragment<D, V, P>{

    private SwipeRefreshLayout refreshView;
    private RecyclerView recyclerView;
    private BaseRefreshAdapter refreshAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean isDownPullRefresh;

    public boolean isDownPullRefresh() {
        return isDownPullRefresh;
    }

    public SwipeRefreshLayout getxRefreshView() {
        return refreshView;
    }

    public BaseRefreshAdapter getRefreshAdapter() {
        return refreshAdapter;
    }

    private boolean isInit = true;


    public abstract void initData();
    public abstract int setContentView();
    //这个具体的实现由子类实现
    public abstract BaseRefreshAdapter bindAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initContentView(view);
        if (isInit){
            isInit = false;
            initData();
        }
    }

    //初始化内容View
    public void initContentView(View contentView){
        //初始化导航条->忽略了->上一期课程讲解过

        //讲解初始化下拉刷新组件
        initRefreshView(contentView);
    }

    private void initRefreshView(View contentView) {
        refreshView = ((SwipeRefreshLayout) contentView.findViewById(R.id.refreshView));

        recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加Adapter
        refreshAdapter = bindAdapter();
        recyclerView.setAdapter(refreshAdapter);
    }

    @Override
    public void bindData(D data, boolean isPullRefresh) {
        super.bindData(data, isPullRefresh);
        if (isDownPullRefresh()){
            //下拉刷新
            getxRefreshView().setRefreshing(false);
        } else {
            //上拉加载

        }
    }

    public void refreshData(boolean isDownPullRefresh){
        this.isDownPullRefresh = isDownPullRefresh;
    }
}
