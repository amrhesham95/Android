package com.example.loginmvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginmvp.screens.MainActivity;

public class Model implements IModel {
    public static final String LOGIN_PREF="login";
    @Override
    public void putUser(Context context, String username, String password) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",username);
        sharedPreferences.edit().putString("password",password);
    }

    public User getUser(Context context, String username, String password) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",username);
        sharedPreferences.edit().putString("password",password);
    }
}
