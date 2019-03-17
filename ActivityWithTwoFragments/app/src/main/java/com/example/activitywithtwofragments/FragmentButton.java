package com.example.activitywithtwofragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentButton extends Fragment {
    Button button;
    public int counter=0;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator=(Communicator) getActivity();
    }

    Communicator communicator;

    public FragmentButton() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment_button, container, false);

        // Inflate the layout for this fragment
        if(savedInstanceState!=null){
            counter=savedInstanceState.getInt("counter");
        }

        button=view.findViewById(R.id.Button);
        button.setOnClickListener((event)->{
            counter++;
            communicator.response(Integer.toString(counter));
        });
        return  view;
    }

}
