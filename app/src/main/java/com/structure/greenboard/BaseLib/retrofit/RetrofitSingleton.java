package com.structure.greenboard.BaseLib.retrofit;

import com.structure.greenboard.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishekdewa on 5/11/2016.
 */
public class RetrofitSingleton {
    private Retrofit retrofit;
    private static RetrofitSingleton retrofitSingleton;

    private RetrofitSingleton() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitSingleton getRetrofitSingletonObj() {
        if (retrofitSingleton == null)
            retrofitSingleton = new RetrofitSingleton();
        return retrofitSingleton;
    }

    public Retrofit getRetrofit() {
        if (retrofitSingleton == null)
            new RetrofitSingleton();
        return retrofit;
    }

    public RetrofitService getRetrofitService() {
        RetrofitService service = getRetrofit().create(RetrofitService.class);
        return service;
    }
}
