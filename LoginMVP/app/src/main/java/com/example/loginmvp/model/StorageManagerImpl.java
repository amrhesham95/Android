package com.example.loginmvp.model;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManagerImpl implements StorageManager {
    public static final String LOGIN_PREF="login";
    @Override
    public void putUser(Context context, String username, String password) {
        SharedPreferences sharedPreferences= context.getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    public boolean checkUser(Context context, String username, String password) {
        String user;
        String pass;
        SharedPreferences sharedPreferences= context.getSharedPreferences(LOGIN_PREF,Context.MODE_PRIVATE);
        user=sharedPreferences.getString("username","default");
        pass=sharedPreferences.getString("password","default");
        if(user.equals(username) && pass.equals(password)){
            return  true;
        }else{
            return  false;
        }
    }
}
