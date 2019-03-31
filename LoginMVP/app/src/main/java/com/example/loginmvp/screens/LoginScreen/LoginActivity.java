package com.example.loginmvp.screens.LoginScreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginmvp.R;
import com.example.loginmvp.screens.SignupScreen.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.IMainView {
    EditText usernameTF;
    EditText passwordTF;
    Button signinBtn;
    Button signupBtn;
    LoginContract.IMainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTF=findViewById(R.id.usrNameTFID);
        passwordTF=findViewById(R.id.passwordTFID);
        signinBtn=findViewById(R.id.signinBtnID);
        signupBtn=findViewById(R.id.signupBtnID);
        mainPresenter=new LoginPresenter(this,getApplicationContext());
        signupBtn.setOnClickListener((event)->{
            Intent intent =new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        signinBtn.setOnClickListener((event)->{
            mainPresenter.signinHandler();
        });

    }

    @Override
    public void loginSucess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupSuccess() {
        Toast.makeText(this, "sign up success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupFailed() {
        Toast.makeText(this, "sign up failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return usernameTF.getText().toString();
    }

    @Override
    public String getPassword() {
       return passwordTF.getText().toString();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
