package com.example.loginmvp.screens;

import android.content.Context;

import com.example.loginmvp.model.IModel;
import com.example.loginmvp.model.Model;

public class Presenter implements MainContract.IMainPresenter{
    public Presenter(MainContract.IMainView mainView) {
        this.mainView = mainView;
        model=new Model();
    }

    MainContract.IMainView mainView;
    IModel model;
    String username;
    String password;
    Context context;
    @Override
    public void signinHandler() {

    }

    @Override
    public void signupHandler() {
        username=mainView.getUsername();
        password=mainView.getPassword();
        context=mainView.getContext();
        model.putUser(context,username,password);
        mainView.signupSuccess();
    }
}
