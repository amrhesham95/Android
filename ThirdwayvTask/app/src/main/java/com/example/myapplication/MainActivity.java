package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Job> originalDataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerViewID);
        originalDataSet =new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        adapter=new MyAdapter(originalDataSet,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        retrofit=new MyRetrofit(ServiceApi.BaseURL).getMyRetrofit();
        ServiceApi serviceApi=retrofit.create(ServiceApi.class);
        serviceApi.getJavaJobsByLocation("java","SED").enqueue(new Callback<ArrayList<Job>>() {
            @Override
            public void onResponse(Call<ArrayList<Job>> call, Response<ArrayList<Job>> response) {
                originalDataSet.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Job>> call, Throwable t) {

            }
        });

    }
}
