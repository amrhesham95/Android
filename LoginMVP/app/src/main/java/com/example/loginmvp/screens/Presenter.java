package com.example.loginmvp.screens;

import android.content.Context;

import com.example.loginmvp.model.IModel;
import com.example.loginmvp.model.Model;

public class Presenter implements MainContract.IMainPresenter{
    public Presenter(MainContract.IMainView mainView,Context context) {
        this.mainView = mainView;
        model=new Model();
        this.context=context;
    }

    MainContract.IMainView mainView;
    IModel model;
    String username;
    String password;
    Context context;
    @Override
    public void signinHandler() {
        username=mainView.getUsername();
        password=mainView.getPassword();
        context=mainView.getContext();
        if(model.checkUser(context,username,password)){
            mainView.loginSucess();
        }else{
            mainView.loginFailed();
        }
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
