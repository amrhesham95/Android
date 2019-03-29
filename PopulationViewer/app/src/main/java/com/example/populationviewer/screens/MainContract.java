package com.example.populationviewer.screens;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.populationviewer.model.CountryBean;

import java.util.ArrayList;

public interface MainContract {

    interface HomeActivityInterface{
        ImageView getImageView();
        void updateUI(int buttonCounter,String rank,String name,String population);
    }
    interface HomeActivityPresenterInterface{
         ImageView getImageView();
         void setImage(Bitmap bitmap);
         void setCountryList(ArrayList<CountryBean> countryList);
         void rightButtonHandler();
         void leftButtonHandler();
    }
}
