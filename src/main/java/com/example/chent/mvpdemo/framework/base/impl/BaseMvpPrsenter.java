package com.example.chent.mvpdemo.framework.base.impl;

import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.base.MvpView;

import java.util.Vector;

/**
 * Created by chent on 2017/9/24.
 */

public class BaseMvpPrsenter<V extends MvpView> implements MvpPresenter<V> {

    private V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }
}
