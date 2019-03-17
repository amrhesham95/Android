package com.example.fragmentlifecycle;


import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    public static final String TAG="fragment";
    @Override
    public void onAttach(Context context) {
        Log.i(TAG,"fragmentOnAttach(2)");
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"fragmentOnCreate(4)");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"fragmentOnActivityCreated(6)");
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onStart() {
        Log.i(TAG,"fragmentOnStart(8)");
        super.onStart();

    }

    @Override
    public void onResume() {
        Log.i(TAG,"fragmentOnResume(10)");
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG,"fragmentOnSaveInstance");
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onPause() {
        Log.i(TAG,"fragmentOnPause");
        super.onPause();

    }

    @Override
    public void onStop() {
        Log.i(TAG,"fragmentOnStop");
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        Log.i(TAG,"fragmentOnDestroyView");
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"fragmentOnDestroy");
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        Log.i(TAG,"fragmentOnDetach");
        super.onDetach();

    }

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG,"fragmentOnCreateView(5)");
        return inflater.inflate(R.layout.fragment_first2, container, false);
    }

}
