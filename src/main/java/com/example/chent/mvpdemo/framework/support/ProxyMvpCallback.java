package com.example.chent.mvpdemo.framework.support;

import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.base.MvpView;

/**
 * Created by chent on 2017/9/24.
 */

public class ProxyMvpCallback<V extends MvpView, P extends MvpPresenter<V>> implements MvpCallback<V, P> {

    private MvpCallback<V, P> mvpCallback;

    public MvpCallback<V, P> getMvpCallback() {
        return mvpCallback;
    }

    public ProxyMvpCallback(MvpCallback<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;
    }

    @Override
    public P createPresenter() {
        P presenter = getMvpCallback().getPresenter();
        if (presenter == null) {
            presenter = getMvpCallback().createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("P层不能为空");
        }
        getMvpCallback().setPresenter(presenter);
        return presenter;
    }

    @Override
    public P getPresenter() {
        return getMvpCallback().getPresenter();
    }

    @Override
    public void setPresenter(P presenter) {
        getMvpCallback().setPresenter(presenter);
    }

    @Override
    public V getMvpView() {
        return getMvpCallback().getMvpView();
    }

    public void attachView() {
        getPresenter().attachView(getMvpView());
    }

    public void detachView() {
        getPresenter().detachView();
    }
}
