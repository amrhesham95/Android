package com.example.countrieswithlistview;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class listViewFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
    }

    public ArrayList<CountryBean> countryList = new ArrayList();
    public static final String link = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
    StringBuffer jsonString;
    ListView listView;
    View view;
    private Handler handler;
    Communicator comm;

    public listViewFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_view, container, false);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                listView = view.findViewById(R.id.listViewID);
                listView.setAdapter(new CountryAdapter(getActivity().getApplicationContext(), R.layout.row_layout, countryList));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                            Intent intent = new Intent(getActivity(), SecondActivity.class);
                            intent.putExtra("rank", countryList.get(position).getRank());
                            intent.putExtra("name", countryList.get(position).getName());
                            intent.putExtra("population", countryList.get(position).getPopulation());
                            intent.putExtra("pic", countryList.get(position).getPicURL());
                            startActivity(intent);
                        }else{
                            comm.communicatorFillData(countryList.get(position).getRank(),countryList.get(position).getName(),countryList.get(position).getPopulation(),
                                    countryList.get(position).getPicURL());
                        }
                        //Toast.makeText(getContext(), countryList.get(position).getRank(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        };

        Thread thread = new Thread(() -> {
            StringBuffer stringBuffer = download();
            try {
                JSONObject reader = new JSONObject(stringBuffer.toString());
                JSONArray worldpopulationArray = reader.getJSONArray("worldpopulation");
                for (int i = 0; i < worldpopulationArray.length(); i++) {
                    JSONObject jsonCountry = worldpopulationArray.getJSONObject(i);
                    CountryBean countryBean = new CountryBean();
                    countryBean.setRank(jsonCountry.getString("rank"));
                    countryBean.setName(jsonCountry.getString("country"));
                    countryBean.setPopulation(jsonCountry.getString("population"));
                    countryBean.setPicURL(jsonCountry.getString("flag").replaceFirst("http", "https"));
                    countryList.add(countryBean);

                }
                //new MyAsyncTask().execute(countryList.get(0).getPicURL());
                handler.sendEmptyMessage(0);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
        thread.start();


        return view;
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
}
