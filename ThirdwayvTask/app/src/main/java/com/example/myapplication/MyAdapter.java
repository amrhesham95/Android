package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public ArrayList<Job> firstDataSet;
    public Context context;
    public MyAdapter(ArrayList<Job> firstDataSet, Context context) {
        this.firstDataSet = firstDataSet;
        this.context=context;

    }
    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView companyTextView;
        public ImageView imgView;
        public TextView dateTextView;
        public Job jobItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener((event)->{
                Intent intent = new Intent(itemView.getContext(),detailedActivity.class);
                itemView.getContext().startActivity(intent);
            });
            titleTextView =itemView.findViewById(R.id.titleRowTextView);
            companyTextView =itemView.findViewById(R.id.companyRowTextView);
            imgView=itemView.findViewById(R.id.imgView);
            dateTextView=itemView.findViewById(R.id.dateRowTextView);

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
        myViewHolder.titleTextView.setText(firstDataSet.get(i).getTitle());
        Glide.with(context).load(firstDataSet.get(i).getCompanyLogo()).override(200,200).into(myViewHolder.imgView);
        myViewHolder.companyTextView.setText(firstDataSet.get(i).getCompany());
        myViewHolder.dateTextView.setText(firstDataSet.get(i).getCreatedAt());

//        myViewHolder.imgView.setOnClickListener((event)->{
//            Intent intent = new Intent(context,detailedActivity.class);
//            intent.putExtra("title",firstDataSet.get(i).)
//            context.startActivity(intent);
//        });


    }

    @Override
    public int getItemCount() {
        return firstDataSet.size();
    }


}
