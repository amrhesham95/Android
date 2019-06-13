package com.example.rxday4celeb;

import android.content.Context;
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
    private ArrayList<Celeb> firstDataSet;
    private Context context;
    public MyAdapter(ArrayList<Celeb> firstDataSet,Context context) {
        this.firstDataSet = firstDataSet;
        this.context=context;

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView firstTextView;
        public TextView secondTextView;
        public CardView cardView;
        public ImageView imgView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstTextView =itemView.findViewById(R.id.rowTextView);
            secondTextView =itemView.findViewById(R.id.rowTextView2);
            imgView=itemView.findViewById(R.id.imgView);

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
        myViewHolder.firstTextView.setText(firstDataSet.get(i).getName());
        //Glide.with(context).load(firstDataSet.get(i).getImage()).into(myViewHolder.imgView);
        Glide.with(context).load(firstDataSet.get(i).getImage()).override(200,200).into(myViewHolder.imgView);
        //Glide.with(context).load(firstDataSet.get(i).getImage()).thumbnail((float) 0.2).into(myViewHolder.imgView);
        myViewHolder.secondTextView.setText(firstDataSet.get(i).getPhone());

    }

    @Override
    public int getItemCount() {
        return firstDataSet.size();
    }


}
