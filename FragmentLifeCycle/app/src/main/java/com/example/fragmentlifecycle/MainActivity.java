package com.example.fragmentlifecycle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="fragment";
    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.i(TAG,"activityOnAttachFragment(3)");
        super.onAttachFragment(fragment);

    }

    @Override
    protected void onResume() {
        Log.i(TAG,"activityOnResume(9)");
        super.onResume();

    }

    @Override
    protected void onPause() {
        Log.i(TAG,"activityOnPause");
        super.onPause();

    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i(TAG,"activityOnStart(7)");

    }

    @Override
    protected void onStop() {
        Log.i(TAG,"activityOnStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"activityOnDestroy");
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG,"activityOnSaveInstance");
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"activityOnCreate(1)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
