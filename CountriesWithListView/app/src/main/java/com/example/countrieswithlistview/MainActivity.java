package com.example.countrieswithlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void communicatorFillData(String rank, String name, String population, String picURL) {
        fullViewFragment rightFragment= (fullViewFragment) getSupportFragmentManager().findFragmentById(R.id.rightFragmentID);
        rightFragment.fillFullViewData(rank,name,population,picURL);
    }
}
