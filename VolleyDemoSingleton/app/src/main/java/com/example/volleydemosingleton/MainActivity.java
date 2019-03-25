package com.example.volleydemosingleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    TextView mTxtDisplay;
    ImageView mImageView;
    JSONArray jsonArray;
    ListView listView;
    Vector<CountryPopulation> countries;
    MyArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.mylist);
        String url = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("MyTag","onResponse JSONObject: "+response.toString());
                        // mTxtDisplay.setText("Response: " + response.toString());
                        countries = MyJSONParser.getCountryVector(response);
                        adapter = new MyArrayAdapter(getApplicationContext(),countries);
                        listView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("MyTag","onErrorResponse Error: "+error.getMessage());
                    }
                });
    }
}
