package com.example.populationviewer.screens;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.populationviewer.model.CountryBean;
import com.example.populationviewer.model.networkservice.NetworkServiceImpl;

import java.util.ArrayList;

public class MainPresenter implements MainContract.HomeActivityPresenterInterface {
    MainContract.HomeActivityInterface homeActivityInterface;
    NetworkServiceImpl networkService;
    ArrayList<CountryBean> countryList;
    private int  buttonCounter=0;
    public MainPresenter(MainContract.HomeActivityInterface homeActivityInterface) {
        this.homeActivityInterface = homeActivityInterface;
        networkService=new NetworkServiceImpl(this);
        //networkService.startAsyncTask(countryList.get(buttonCounter).getPicURL());
        //homeActivityInterface.updateUI(buttonCounter,countryList.get(buttonCounter).getRank(),countryList.get(buttonCounter).getName(),countryList.get(buttonCounter).getPopulation());

    }

    public void rightButtonHandler(){
        buttonCounter++;
        buttonCounter=buttonCounter%10;
//        networkService.startAsyncTask(countryList.get(buttonCounter).getPicURL());
//        homeActivityInterface.updateUI(buttonCounter,countryList.get(buttonCounter).getRank(),countryList.get(buttonCounter).getName(),countryList.get(buttonCounter).getPopulation());
    }

    public void leftButtonHandler(){
        buttonCounter++;
        buttonCounter=buttonCounter%10;
        networkService.startAsyncTask(countryList.get(buttonCounter).getPicURL());
        homeActivityInterface.updateUI(buttonCounter,countryList.get(buttonCounter).getRank(),countryList.get(buttonCounter).getName(),countryList.get(buttonCounter).getPopulation());
    }

    @Override
    public ImageView getImageView() {
        return homeActivityInterface.getImageView();
    }

    @Override
    public void setImage(Bitmap bitmap) {
        getImageView().setImageBitmap(bitmap);
    }

    @Override
    public void setCountryList(ArrayList<CountryBean> countryList) {
        this.countryList=countryList;
    }
}
