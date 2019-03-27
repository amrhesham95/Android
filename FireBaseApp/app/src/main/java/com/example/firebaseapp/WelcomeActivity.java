package com.example.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {
    Button nextButton;
    Button closeButton;
    Button viewButton;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    EditText titleField;
    EditText bodyField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("message");
        closeButton=findViewById(R.id.closeBtn);
        nextButton=findViewById(R.id.nextBtn);
        viewButton=findViewById(R.id.viewButtonID);
        titleField=findViewById(R.id.textTitle);
        bodyField=findViewById(R.id.textBody);
        firebaseAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        closeButton.setOnClickListener((v)->{
            signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        nextButton.setOnClickListener(v -> {
            Message message=new Message(titleField.getText().toString(),bodyField.getText().toString(),firebaseAuth.getCurrentUser().getUid());
            database.getReference(firebaseAuth.getCurrentUser().getUid()).child(myRef.push().getKey()).setValue(message);

        });

        viewButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,listActivity.class);
            startActivity(intent);
        });

    }
    private void signOut() {
        // Firebase sign out
        firebaseAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        //updateUI(null);
                    }
                });
    }

}
