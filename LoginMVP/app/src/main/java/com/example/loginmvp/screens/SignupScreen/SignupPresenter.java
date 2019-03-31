package com.example.loginmvp.screens.SignupScreen;

import android.content.Context;

import com.example.loginmvp.model.StorageManager;
import com.example.loginmvp.model.StorageManagerImpl;
import com.example.loginmvp.screens.LoginScreen.LoginContract;

public class SignupPresenter implements SignupContract.SignupPresenter {
    SignupContract.SignupView signupView;
    StorageManager model;
    String username;
    String password;
    Context context;
    public SignupPresenter(SignupContract.SignupView signupView, Context context) {
        this.signupView = signupView;
        model=new StorageManagerImpl();
        this.context=context;
    }
    @Override
    public void signupHandler() {
        username=signupView.getUsername();
        password=signupView.getPassword();
        context=signupView.getContext();
        model.putUser(context,username,password);
        signupView.signupSuccess();
    }
}
