package com.example.populationviewer.model.networkservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.example.populationviewer.model.CountryBean;
import com.example.populationviewer.screens.MainContract;

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

public class NetworkServiceImpl {
    public static final String link = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    StringBuffer jsonString;

    public static ArrayList<CountryBean> countryList = new ArrayList();
    private Handler handler;
    MainContract.HomeActivityPresenterInterface homeActivityPresenterInterface;

    public NetworkServiceImpl(MainContract.HomeActivityPresenterInterface homeActivityPresenterInterface) {
        this.homeActivityPresenterInterface = homeActivityPresenterInterface;

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
               homeActivityPresenterInterface.setCountryList(getCountries());
            }
        };
        kharra();
    }

    public class MyAsyncTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
          // homeActivityPresenterInterface.getImageView().setImageBitmap(bitmap);
           homeActivityPresenterInterface.setImage(bitmap);
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

    public void kharra(){
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
    public ArrayList<CountryBean>getCountries(){
        return  countryList;
    }

    public void startAsyncTask(String picURL){
        new MyAsyncTask().execute(picURL);
    }

}
