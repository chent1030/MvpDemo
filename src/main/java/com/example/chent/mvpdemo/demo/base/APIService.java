package com.example.chent.mvpdemo.demo.base;

import com.example.chent.mvpdemo.demo.bean.MoviesBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chent on 2017/9/25.
 */

public interface APIService {

    @GET("v2/movie/top250")
    Observable<MoviesBean> getMovies(@Query("start") int start, @Query("count") int count);
}
