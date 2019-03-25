package com.example.volleydemosingleton;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;
import java.util.Vector;

/**
 * Created by iti on 21/02/2017.
 */

public class MyArrayAdapter extends ArrayAdapter<CountryPopulation> {
    List<CountryPopulation> countries;
    Context context;
    ImageLoader imageLoader;
    public MyArrayAdapter(Context context, Vector<CountryPopulation> objects) {
        super(context, R.layout.single_row, R.id.country_name, objects);
        this.context = context;
        countries = objects;
        Log.i("MyTag","The Size of the Vector"+countries.size());
        imageLoader = MySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("MyTag","The Position is: "+position);
        View rowView= convertView;
        ViewHolder viewHolder;
        if (rowView == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.single_row, parent,false);
            viewHolder = new ViewHolder(rowView);
            rowView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)rowView.getTag();
        }
        viewHolder.getCountryName().setText(countries.get(position).getCountry());
        viewHolder.getFlag().setImageUrl(countries.get(position).getUrl(),imageLoader);
        viewHolder.getRank().setText(countries.get(position).getRank());
        viewHolder.getPopulation().setText(countries.get(position).getPopulation());
        return rowView;

    }
}
