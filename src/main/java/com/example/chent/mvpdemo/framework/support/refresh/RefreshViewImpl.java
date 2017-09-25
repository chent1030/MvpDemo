package com.example.chent.mvpdemo.framework.support.refresh;

import android.view.View;

import com.example.chent.mvpdemo.R;
import com.example.chent.mvpdemo.framework.support.refresh.animator.DefaultLceAnimator;
import com.example.chent.mvpdemo.framework.support.refresh.animator.LCEAnimator;

/**
 * Created by chent on 2017/9/25.
 */

public class RefreshViewImpl<D> implements RefreshView<D> {

    private View loadingView;
    private View contentView;
    private View errorView;

    private LCEAnimator animator;

    public LCEAnimator getAnimator() {
        if (this.animator == null) {
            this.animator = new DefaultLceAnimator();
        }
        return animator;
    }

    public void setAnimator(LCEAnimator animator) {
        this.animator = animator;
    }

    public void initView(View rootView) {
        if (rootView == null) {
            throw new NullPointerException("根View不能为空");
        }
        if (this.loadingView == null) {
            this.loadingView = rootView.findViewById(R.id.loadingView);
        }
        if (this.contentView == null) {
            this.contentView = rootView.findViewById(R.id.contentView);
        }
        if (this.errorView == null) {
            this.errorView = rootView.findViewById(R.id.errorView);
        }
        if (loadingView == null) {
            throw new NullPointerException("加载页不能够为空");
        }
        if (contentView == null) {
            throw new NullPointerException("内容页不能够为空");
        }
        if (errorView == null) {
            throw new NullPointerException("错误页不能够为空");
        }
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        if (!isPullRefresh) {
            getAnimator().showLoadingView(this.loadingView, this.contentView, this.errorView);
        }
    }

    @Override
    public void showContent(boolean isPullRefresh) {
        if (!isPullRefresh) {
            getAnimator().showContentView(this.loadingView, this.contentView, this.errorView);
        }
    }

    @Override
    public void showError(boolean isPullRefresh, String errorMsg) {
        if (!isPullRefresh) {
            getAnimator().showErrorView(this.loadingView, this.contentView, this.errorView, errorMsg);
        }
    }

    @Override
    public void bindData(D data, boolean isPullRefresh) {

    }

    @Override
    public void loadData(boolean isPullRefresh) {

    }
}
