package com.example.activitywithtwofragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTextView extends Fragment {
    TextView textView;
    public String initialText;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("initialText",initialText);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView=getActivity().findViewById(R.id.textViewID);

    }


    public void changeData(String data){
        initialText=data;
        textView.setText(data);
    }
    public FragmentTextView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView tView;
        View view=inflater.inflate(R.layout.fragment_fragment_text_view, container, false);
        if(savedInstanceState!=null){
            tView=view.findViewById(R.id.textViewID);
            tView.setText(savedInstanceState.getString("initialText"));
        }
        return view;
    }

}
