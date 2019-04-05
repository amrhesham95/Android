package com.example.retrofitcountries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/tutorial/jsonparsetutorial.txt")
    Call <Country> getArrayObject();
    //Call <List<Worldpopulation>> getWorldpopulation();
}
