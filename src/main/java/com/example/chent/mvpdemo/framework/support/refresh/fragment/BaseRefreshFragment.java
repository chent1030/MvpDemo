package com.example.chent.mvpdemo.framework.support.refresh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chent.mvpdemo.demo.adapter.RefreshAdapter;
import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.support.fragment.MvpFragment;
import com.example.chent.mvpdemo.framework.support.refresh.RefreshView;
import com.example.chent.mvpdemo.framework.support.refresh.RefreshViewImpl;
import com.example.chent.mvpdemo.framework.support.refresh.animator.LCEAnimator;

/**
 * Created by chent on 2017/9/25.
 */

public abstract class BaseRefreshFragment<D, V extends RefreshView<D>, P extends MvpPresenter<V>>
        extends MvpFragment<V, P> implements RefreshView<D> {

    private RefreshViewImpl<D> refreshView;

    private RefreshViewImpl<D> getRefreshView(){
        if (refreshView == null){
            refreshView = new RefreshViewImpl<>();
        }
        return refreshView;
    }

    public void setLceAnimator(LCEAnimator lceAnimator) {
        this.refreshView.setAnimator(lceAnimator);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getRefreshView().initView(view);
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        getRefreshView().showLoading(isPullRefresh);
    }

    @Override
    public void showContent(boolean isPullRefresh) {
        getRefreshView().showContent(isPullRefresh);
    }

    @Override
    public void showError(boolean isPullRefresh, String errorMsg) {
        getRefreshView().showError(isPullRefresh, errorMsg);
    }

    @Override
    public void bindData(D data, boolean isPullRefresh) {
        getRefreshView().bindData(data, isPullRefresh);
    }

    @Override
    public void loadData(boolean isPullRefresh) {
        getRefreshView().loadData(isPullRefresh);
    }
}
