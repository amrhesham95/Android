package com.example.volleydemosingleton;

import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

public class ViewHolder {
	View convertView;
	TextView countryName;
	TextView rank;
	TextView population;
	NetworkImageView flag;

	public TextView getPopulation() {
		if(population == null)
			population = (TextView) convertView.findViewById(R.id.population);
		return population;
	}
	
	public ViewHolder(View v) {
		convertView = v;
	}	
	
	public TextView getCountryName() {
		if(countryName == null)
			countryName = (TextView) convertView.findViewById(R.id.country_name);
		return countryName;
	}
	
	public TextView getRank() {
		if(rank == null)
			rank = (TextView) convertView.findViewById(R.id.rank);
		return rank;
	}
	
	public NetworkImageView getFlag() {
		if(flag == null)
			flag = (NetworkImageView) convertView.findViewById(R.id.imgflag);

		return flag;
	}

}
