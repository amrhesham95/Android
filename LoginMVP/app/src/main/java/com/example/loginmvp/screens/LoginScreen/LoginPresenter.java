package com.example.loginmvp.screens.LoginScreen;

import android.content.Context;

import com.example.loginmvp.model.StorageManager;
import com.example.loginmvp.model.StorageManagerImpl;
import com.example.loginmvp.screens.LoginScreen.LoginContract;

public class LoginPresenter implements LoginContract.IMainPresenter{
    public LoginPresenter(LoginContract.IMainView mainView, Context context) {
        this.mainView = mainView;
        model=new StorageManagerImpl();
        this.context=context;
    }

    LoginContract.IMainView mainView;
    StorageManager model;
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


}
