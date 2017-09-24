package com.example.chent.mvpdemo.framework.support.activity;

import android.os.Bundle;

import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.base.MvpView;
import com.example.chent.mvpdemo.framework.support.MvpCallback;
import com.example.chent.mvpdemo.framework.support.ProxyMvpCallback;

/**
 * Created by chent on 2017/9/24.
 */

public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenter<V>> implements ActivityMvpDelegate<V, P> {
    private MvpCallback<V, P> mvpCallback;
    private ProxyMvpCallback<V, P> proxyMvpCallback;

    public ActivityMvpDelegateImpl(MvpCallback<V, P> mvpCallback) {
        this.mvpCallback = mvpCallback;
    }

    private ProxyMvpCallback<V, P> getProxyMvpCallback() {
        if (this.proxyMvpCallback == null) {
            this.proxyMvpCallback = new ProxyMvpCallback<V, P>(this.mvpCallback);
        }
        return proxyMvpCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getProxyMvpCallback().createPresenter();
        getProxyMvpCallback().attachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        getProxyMvpCallback().detachView();
    }
}
