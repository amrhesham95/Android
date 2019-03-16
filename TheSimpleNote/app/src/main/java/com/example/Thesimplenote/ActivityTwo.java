package com.example.Thesimplenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {
    TextView titleText;
    TextView bodyText;
    Button closeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        titleText= findViewById(R.id.textTitle);
        bodyText= findViewById(R.id.textBody);
        closeBtn=(Button) findViewById(R.id.closeBtn2);
        Intent incomingIntent=new Intent(getIntent());
        titleText.setText(incomingIntent.getStringExtra("title"));
        bodyText.setText(incomingIntent.getStringExtra("body"));

        closeBtn.setOnClickListener((event)->finish());
    }
}
