package com.example.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Button signUpBtn;
    Button loginBtn;
    String email="test";
    String password="test";
    EditText emailTextField;
    EditText passwordTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUpBtn=findViewById(R.id.SignUpButtonID);
        loginBtn=findViewById(R.id.loginButtonID);
        emailTextField=findViewById(R.id.userNameField);
        passwordTextField=findViewById(R.id.passwordField);



        firebaseAuth=FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener((v)->{
            email=emailTextField.getText().toString();
            password=passwordTextField.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("log","success");
                                Toast.makeText(loginActivity.this, "success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("log",task.getException().toString());
                                Toast.makeText(loginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                               //updateUI(null);
                            }

                            // ...
                        }
                    });
        });
        loginBtn.setOnClickListener((v)->{
            email=emailTextField.getText().toString();
            password=passwordTextField.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent=new Intent(loginActivity.this,WelcomeActivity.class);
                                startActivity(intent);
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("log", task.getException().toString(), task.getException());
                                Toast.makeText(loginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        });

    }

}
