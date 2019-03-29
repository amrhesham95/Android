package com.example.populationviewer.screens;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.populationviewer.R;
import com.example.populationviewer.model.CountryBean;
import com.example.populationviewer.model.networkservice.NetworkServiceImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class HomeActivity extends AppCompatActivity implements MainContract.HomeActivityInterface{
    NetworkServiceImpl networkService;
    MainContract.HomeActivityPresenterInterface homeActivityPresenterInterface;
    TextView rankTextView;
    TextView populationTextView;
    TextView nameTextView;
    ImageView imageView;
    Button leftButton;
    Button rightButton;
    public static final String link = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeActivityPresenterInterface=new MainPresenter(this);
        rankTextView = findViewById(R.id.rankTextViewID);
        populationTextView = findViewById(R.id.popTextViewID);
        nameTextView = findViewById(R.id.nameTextViewID);
        imageView = findViewById(R.id.imageViewID);
        leftButton = findViewById(R.id.leftButtonId);
        rightButton = findViewById(R.id.rightButtonId);

        rightButton.setOnClickListener((v)->{
            homeActivityPresenterInterface.rightButtonHandler();
        });

        leftButton.setOnClickListener((v)->{
            homeActivityPresenterInterface.leftButtonHandler();
        });


    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }


    public void updateUI(int buttonCounter,String rank,String name,String population){
        HomeActivity.this.runOnUiThread(()->{
            rankTextView.setText(rank);
            nameTextView.setText(name);
            populationTextView.setText(population);
        });
    }
}

