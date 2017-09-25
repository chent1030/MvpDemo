package com.example.chent.mvpdemo.demo.utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chent on 2017/9/25.
 */

public class HttpUtils {

    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit;

    private HttpUtils() {

    }

    public static Retrofit newInstance(String baseUrl) {
        mRetrofit = null;
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.MINUTES)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit;
    }
}
