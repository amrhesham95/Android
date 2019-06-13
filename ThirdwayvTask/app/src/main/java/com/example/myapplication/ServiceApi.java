package com.example.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {
    String BaseURL="https://jobs.github.com/";



    @GET("/positions.json")
    Call<ArrayList<Job>>getJavaJobsByLocation(@Query("description") String language,@Query("location") String location);
}
