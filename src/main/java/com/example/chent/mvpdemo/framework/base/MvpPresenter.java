package com.example.chent.mvpdemo.framework.base;

/**
 * Created by chent on 2017/9/24.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
