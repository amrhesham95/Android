package com.example.orientationgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter=0;
    TextView counterTextView;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("counter",counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            counter=savedInstanceState.getInt("counter");
            counter++;
            setContentView(R.layout.activity_main);
            counterTextView = findViewById(R.id.counterTextId);
            counterTextView.setText(Integer.toString(counter));
        }


    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
