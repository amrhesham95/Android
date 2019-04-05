package com.example.retrofitcountries;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL="https://www.androidbegin.com";
    public static List<Worldpopulation> countries;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listViewID);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service=retrofit.create(RetrofitService.class);
        Call<Country> arrayObject = service.getArrayObject();
        arrayObject.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                countries= response.body().getWorldpopulation();
                listView.setAdapter(new CountryAdapter(getApplicationContext(),R.layout.row_layout,countries));
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
