package com.example.chent.mvpdemo.demo.view;

import android.content.Context;

import com.example.chent.mvpdemo.demo.base.OnHttpCallBack;
import com.example.chent.mvpdemo.demo.bean.MoviesBean;
import com.example.chent.mvpdemo.framework.base.impl.BaseMvpPrsenter;
import com.example.chent.mvpdemo.framework.support.refresh.RefreshView;

/**
 * Created by chent on 2017/9/25.
 */

public class MoviePresenter extends BaseMvpPrsenter<RefreshView<MoviesBean>> {

    private MovieModel model;

    private int page;

    public MoviePresenter(){
        model = new MovieModel();
    }

    public void getList(boolean isDownPullRefresh, final boolean isPullRefresh){
        if (isDownPullRefresh){
            page = 0;
        }

        getView().showLoading(isPullRefresh);
        this.model.getList(page, 10, new OnHttpCallBack<MoviesBean>() {
            @Override
            public void onSuccessful(MoviesBean moviesBean) {
                if (moviesBean == null){
                    getView().bindData(null, isPullRefresh);
                } else {
                    page++;
                    getView().bindData((MoviesBean) moviesBean, isPullRefresh);
                }
                //显示内容View
                getView().showContent(isPullRefresh);
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().showError(isPullRefresh, errorMsg);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
