package com.example.weeklistviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewHolder {
    public TextView getNameTextView() {
        if(nameTextView==null)
            nameTextView=currentView.findViewById(R.id.textViewID);
        return nameTextView;
    }

    public ImageView getImageView() {
        if(imageView==null)
            imageView=currentView.findViewById(R.id.imageViewID);
        return imageView;
    }

    private TextView nameTextView;
    private ImageView imageView;
    private View currentView;

    public ViewHolder(View currentView) {
        this.currentView = currentView;
    }



}
