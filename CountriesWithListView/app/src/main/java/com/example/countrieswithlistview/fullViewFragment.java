package com.example.countrieswithlistview;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class fullViewFragment extends Fragment {
    View view;
    TextView rankTextView;
    TextView populationTextView;
    TextView nameTextView;
    ImageView imageView;

    public fullViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState!=null){
            Toast.makeText(getActivity(), "please flip the mobile or press back :D", Toast.LENGTH_SHORT).show();
        }
        view = inflater.inflate(R.layout.fragment_full_view, container, false);
        rankTextView = view.findViewById(R.id.rankTextViewID);
        populationTextView = view.findViewById(R.id.popTextViewID);
        nameTextView = view.findViewById(R.id.nameTextViewID);
        imageView = view.findViewById(R.id.imageViewID);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rankTextView.setText(getActivity().getIntent().getStringExtra("rank"));
            nameTextView.setText(getActivity().getIntent().getStringExtra("name"));
            populationTextView.setText(getActivity().getIntent().getStringExtra("population"));
            new MyAsyncTask().execute(getActivity().getIntent().getStringExtra("pic"));
        }
        return view;

    }

    public class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {
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
            URL url = null;
            InputStream inputStream = null;
            Bitmap bitmap = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                inputStream = httpsURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
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

    public void fillFullViewData(String rank,String name,String population,String picURL){
        rankTextView.setText(rank);
        nameTextView.setText(name);
        populationTextView.setText(population);
        new MyAsyncTask().execute(picURL);
    }

}
