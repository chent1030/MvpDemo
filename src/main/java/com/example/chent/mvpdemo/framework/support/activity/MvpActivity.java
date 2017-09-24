package com.example.chent.mvpdemo.framework.support.activity;

import android.app.Activity;

import com.example.chent.mvpdemo.framework.base.MvpPresenter;
import com.example.chent.mvpdemo.framework.base.MvpView;
import com.example.chent.mvpdemo.framework.support.MvpCallback;

/**
 * Created by chent on 2017/9/24.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends Activity implements MvpCallback<V, P>, MvpView {

    private ActivityMvpDelegate<V, P> activityMvpDelegate;

    private ActivityMvpDelegate getActivityMvpDelegate() {
        if (this.activityMvpDelegate == null) {
            this.activityMvpDelegate = new ActivityMvpDelegateImpl<V, P>(this);
        }
        return this.activityMvpDelegate;
    }

    private P presenter;

    @Override
    public P createPresenter() {
        return presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getActivityMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getActivityMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getActivityMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityMvpDelegate().onDestroy();
    }
}
