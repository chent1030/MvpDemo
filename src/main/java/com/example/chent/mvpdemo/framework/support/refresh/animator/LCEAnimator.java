package com.example.chent.mvpdemo.framework.support.refresh.animator;

import android.view.View;

/**
 * Created by chent on 2017/9/25.
 */

public interface LCEAnimator {

    /**
     * 加载页
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showLoadingView(View loadingView, View contentView, View errorView);

    /**
     * 内容页
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showContentView(View loadingView, View contentView, View errorView);

    /**
     * 错误页
     * @param loadingView
     * @param contentView
     * @param errorView
     */
    void showErrorView(View loadingView, View contentView, View errorView, String errorMsg);
}
