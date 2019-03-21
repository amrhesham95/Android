package com.example.populationviewer;


import android.app.Activity;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    TextView rankTextView;
    TextView populationTextView;
    TextView nameTextView;
    ImageView imageView;
    Button leftButton;
    Button rightButton;
    public static final String link = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    StringBuffer jsonString;
    private int  buttonCounter=0;
    public static ArrayList<CountryBean> countryList = new ArrayList();
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rankTextView = findViewById(R.id.rankTextViewID);
        populationTextView = findViewById(R.id.popTextViewID);
        nameTextView = findViewById(R.id.nameTextViewID);
        imageView = findViewById(R.id.imageViewID);
        leftButton = findViewById(R.id.leftButtonId);
        rightButton = findViewById(R.id.rightButtonId);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                rankTextView.setText(countryList.get(buttonCounter).getRank());
                nameTextView.setText(countryList.get(buttonCounter).getName());
                populationTextView.setText(countryList.get(buttonCounter).getPopulation());
            }
        };

        Thread thread = new Thread(() -> {
            StringBuffer stringBuffer = download();
            try {
                JSONObject reader = new JSONObject(stringBuffer.toString());
                JSONArray worldpopulationArray = reader.getJSONArray("worldpopulation");
                for(int i=0;i<worldpopulationArray.length();i++){
                    JSONObject jsonCountry=worldpopulationArray.getJSONObject(i);
                   CountryBean countryBean=new CountryBean();
                   countryBean.setRank(jsonCountry.getString("rank"));
                   countryBean.setName(jsonCountry.getString("country"));
                   countryBean.setPopulation(jsonCountry.getString("population"));
                   countryBean.setPicURL(jsonCountry.getString("flag").replaceFirst("http","https"));
                   countryList.add(countryBean);

                }
                new MyAsyncTask().execute(countryList.get(0).getPicURL());
                handler.sendEmptyMessage(0);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
        thread.start();

        rightButton.setOnClickListener((v)->{
            buttonCounter++;
            buttonCounter=buttonCounter%10;
            new MyAsyncTask().execute(countryList.get(buttonCounter).getPicURL());
            MainActivity.this.runOnUiThread(()->{
                rankTextView.setText(countryList.get(buttonCounter).getRank());
                nameTextView.setText(countryList.get(buttonCounter).getName());
                populationTextView.setText(countryList.get(buttonCounter).getPopulation());
            });
        });

        leftButton.setOnClickListener((v)->{

            if(buttonCounter==0){
                buttonCounter=10;
            }
            buttonCounter--;
            buttonCounter=buttonCounter%10;
            new MyAsyncTask().execute(countryList.get(buttonCounter).getPicURL());
            MainActivity.this.runOnUiThread(()->{
                rankTextView.setText(countryList.get(buttonCounter).getRank());
                nameTextView.setText(countryList.get(buttonCounter).getName());
                populationTextView.setText(countryList.get(buttonCounter).getPopulation());
            });
        });


    }

    public StringBuffer download() {
        InputStream inputStream = null;
        try {
            jsonString = new StringBuffer();
            URL url = new URL(link);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            inputStream = httpsURLConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }

    public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url= null;
            InputStream inputStream = null;
            Bitmap bitmap = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
                inputStream= httpsURLConnection.getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            return bitmap;
        }
    }
}

