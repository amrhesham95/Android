package com.example.loginmvp.screens.SignupScreen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginmvp.R;
import com.example.loginmvp.screens.LoginScreen.LoginContract;
import com.example.loginmvp.screens.LoginScreen.LoginPresenter;

public class SignupActivity extends AppCompatActivity implements SignupContract.SignupView {
    EditText usernameTF;
    EditText passwordTF;
    Button signinBtn;
    Button signupBtn;
    SignupContract.SignupPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameTF=findViewById(R.id.usrNameTFID);
        passwordTF=findViewById(R.id.passwordTFID);
        signinBtn=findViewById(R.id.signinBtnID);
        signupBtn=findViewById(R.id.signupBtnID);
        presenter=new SignupPresenter(this,getApplicationContext());
        signupBtn.setOnClickListener((event)->{
            presenter.signupHandler();
        });
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
