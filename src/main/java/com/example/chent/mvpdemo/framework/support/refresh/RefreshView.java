package com.example.chent.mvpdemo.framework.support.refresh;

import com.example.chent.mvpdemo.framework.base.MvpView;

/**
 * Created by chent on 2017/9/25.
 */

public interface RefreshView<D> extends MvpView {

    void showLoading(boolean isPullRefresh);

    void showContent(boolean isPullRefresh);

    void showError(boolean isPullRefresh, String errorMsg);

    void bindData(D data, boolean isPullRefresh);

    void loadData(boolean isPullRefresh);
}
