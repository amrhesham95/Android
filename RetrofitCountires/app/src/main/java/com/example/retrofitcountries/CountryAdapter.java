package com.example.retrofitcountries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Worldpopulation> {

    List<Worldpopulation> countries;
    Context context;
    public CountryAdapter(Context context, int resource, List<Worldpopulation> countries) {
        super(context, resource, countries);
        this.context=context;
        this.countries=countries;
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

        //holder.getImageView().setImageResource(countries[position].getResourcePictureID());
        Glide.with(context).load(countries.get(position).getFlag()).into(holder.getImageView());
        holder.getNameTextView().setText(countries.get(position).getCountry());
        return  row;

    }
}
