package com.example.countrieswithlistview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    public TextView getNameTextView() {
        if(nameTextView==null)
            nameTextView=currentView.findViewById(R.id.rowTextViewID);
        return nameTextView;
    }

    public ImageView getImageView() {
        if(imageView==null)
            imageView=currentView.findViewById(R.id.rowImageViewID);
        return imageView;
    }

    private TextView nameTextView;
    private ImageView imageView;
    private View currentView;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }



}
