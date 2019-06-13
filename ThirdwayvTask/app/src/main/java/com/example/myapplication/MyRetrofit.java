package com.example.myapplication;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    String baseUrl;
    Retrofit retrofit;
    MyRetrofit(String url) {
       retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }

    public Retrofit getMyRetrofit(){
        return retrofit;
    }

}
