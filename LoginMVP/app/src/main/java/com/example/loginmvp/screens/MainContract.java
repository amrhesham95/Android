package com.example.loginmvp.screens;

import android.content.Context;

public interface MainContract {
    interface IMainPresenter{

        void signinHandler();
        void  signupHandler();
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
