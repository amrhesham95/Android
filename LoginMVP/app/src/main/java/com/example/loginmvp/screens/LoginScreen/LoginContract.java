package com.example.loginmvp.screens.LoginScreen;

import android.content.Context;

public interface LoginContract {
    interface IMainPresenter{

        void signinHandler();

    }

    interface IMainView{
        void loginSucess();
        void loginFailed();
        void signupSuccess();
        void signupFailed();
        String getUsername();
        String getPassword();
        Context getContext();
    }
}
