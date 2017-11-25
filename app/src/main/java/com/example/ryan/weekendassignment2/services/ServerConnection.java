package com.example.ryan.weekendassignment2.services;

import com.example.ryan.weekendassignment2.util.API_List;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ryan on 24/11/2017.
 */

public class ServerConnection {

    public static Retrofit retrofit;
    public static OkHttpClient okHttpClient;

    public static RequestInterface getServerConnection(){

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_List.BASE_URL)
                .build();

        return retrofit.create(RequestInterface.class);

    }
}
