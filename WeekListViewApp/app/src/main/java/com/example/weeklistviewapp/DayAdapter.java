package com.example.weeklistviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class DayAdapter extends ArrayAdapter<DayBean> {
    DayBean[] days;
    Context context;
    public DayAdapter(Context context, int resource, int textViewResourceId, DayBean[] days) {
        super(context, resource, textViewResourceId, days);
        this.context=context;
        this.days=days;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup listView) {
        View row=convertView;
        ViewHolder holder;
        if(row==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row_layout,listView,false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder=(ViewHolder)row.getTag();
        }
//        ImageView imageView=row.findViewById(R.id.imageViewID);
//        TextView textView=row.findViewById(R.id.textViewID);
//        imageView.setImageResource(days[position].getResourcePictureID());
//        textView.setText(days[position].getName());

        holder.getImageView().setImageResource(days[position].getResourcePictureID());
        holder.getNameTextView().setText(days[position].getName());
        return  row;

    }
}
