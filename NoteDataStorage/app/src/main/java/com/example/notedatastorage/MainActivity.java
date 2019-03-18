package com.example.notedatastorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String LOGIN_PREF="loginPref";
    Button loginBtn;
    Button signUpBtn;
    EditText userName;
    EditText password;
    String userNameCheck;
    String passwordCheck;
    Map skipLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userName=findViewById(R.id.userNameField);
        password=findViewById(R.id.passwordField);
        signUpBtn=findViewById(R.id.SignUpButtonID);
        signUpBtn.setOnClickListener(v -> {
            SharedPreferences loginPref=getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=loginPref.edit();
            editor.putString(userName.getText().toString(),userName.getText().toString());
            editor.putString(password.getText().toString(),password.getText().toString());//
            editor.commit();
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        });

        loginBtn=findViewById(R.id.loginButtonID);

        loginBtn.setOnClickListener(v -> {
            userNameCheck=getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE).getString(userName.getText().toString(),"false");
            passwordCheck=getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE).getString(password.getText().toString(),"false");// revise this step

                if(!(userNameCheck.equals("false")||passwordCheck.equals("false"))){
                    Intent outIntent=new Intent(MainActivity.this,NoteWelcomePage.class);
                    startActivity(outIntent);
                }

        });

        //to skip the login screen
        skipLogin=getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE).getAll();
        if(skipLogin.size()>0){
            Intent outIntent=new Intent(MainActivity.this,NoteWelcomePage.class);
            startActivity(outIntent);
        }

    }
}
