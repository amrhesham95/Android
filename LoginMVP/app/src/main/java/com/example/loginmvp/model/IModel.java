package com.example.loginmvp.model;

import android.content.Context;

public interface IModel {
    public void putUser(Context context, String username, String password);
    public boolean checkUser(Context context, String username, String password);
}
