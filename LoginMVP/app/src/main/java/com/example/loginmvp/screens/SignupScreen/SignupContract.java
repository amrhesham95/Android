package com.example.loginmvp.screens.SignupScreen;

import android.content.Context;

public interface SignupContract {
    interface SignupPresenter{

        void  signupHandler();
    }

    interface SignupView{
        void signupSuccess();
        void signupFailed();
        String getUsername();
        String getPassword();
        Context getContext();
    }
}
