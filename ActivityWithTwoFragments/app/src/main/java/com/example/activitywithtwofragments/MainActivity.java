package com.example.activitywithtwofragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Communicator{
    FragmentTextView fragmentTextView;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("nouran","destroy is called");
    }


    @Override
    public void response(String data) {

      fragmentTextView.changeData(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTextView= (FragmentTextView) getSupportFragmentManager().findFragmentById(R.id.fragmentID);
        if(savedInstanceState==null){
            FragmentButton myFragment =new FragmentButton();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.SecondLayout,myFragment,"myFragment");
            fragmentTransaction.commit();
        }


    }
}
