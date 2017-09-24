package com.example.chent.mvpdemo.framework.support;

import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.base.MvpView;

/**
 * Created by chent on 2017/9/24.
 */

public interface MvpCallback<V extends MvpView, P extends MvpPresenter<V>> {

    P createPresenter();

    P getPresenter();

    void setPresenter(P presenter);

    V getMvpView();
}
