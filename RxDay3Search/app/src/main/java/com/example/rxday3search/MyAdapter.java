package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> firstDataSet;
    private ArrayList<String> secondDataSet;
    public MyAdapter(ArrayList<String> firstDataSet,ArrayList<String> secondDataSet) {
        this.firstDataSet = firstDataSet;
        this.secondDataSet=secondDataSet;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView firstTextView;
        public TextView secondTextView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstTextView =itemView.findViewById(R.id.rowTextView);
            secondTextView= itemView.findViewById(R.id.secondTextView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem,viewGroup,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.firstTextView.setText(firstDataSet.get(i));
        myViewHolder.secondTextView.setText(secondDataSet.get(i));
    }

    @Override
    public int getItemCount() {
        return firstDataSet.size();
    }


}
