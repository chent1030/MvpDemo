package com.example.chent.mvpdemo.demo.view;

import com.example.chent.mvpdemo.demo.base.APIService;
import com.example.chent.mvpdemo.demo.base.GlobalField;
import com.example.chent.mvpdemo.demo.base.OnHttpCallBack;
import com.example.chent.mvpdemo.demo.bean.MoviesBean;
import com.example.chent.mvpdemo.demo.utils.HttpUtils;
import com.example.chent.mvpdemo.framework.base.impl.BaseMvpModel;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by chent on 2017/9/25.
 */

public class MovieModel extends BaseMvpModel {

    public void getList(int start, int count, final OnHttpCallBack<MoviesBean> callBack){
        HttpUtils.newInstance(GlobalField.MOVIE_TOP250_URL)
                .create(APIService.class)
                .getMovies(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MoviesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MoviesBean value) {
                        callBack.onSuccessful(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFailed("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFailed("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFailed("网络连接超时!!");
                        } else {
                            callBack.onFailed("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
}
