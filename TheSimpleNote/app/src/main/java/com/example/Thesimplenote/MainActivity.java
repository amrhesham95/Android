package com.example.Thesimplenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button nextBtn;
    Button closeBtn;
    EditText titleText;
    EditText bodyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextBtn=(Button) findViewById(R.id.nextBtn);
        closeBtn=(Button) findViewById(R.id.closeBtn);
        titleText=(EditText) findViewById(R.id.textTitle);
        bodyText=(EditText) findViewById(R.id.textBody);
        closeBtn.setOnClickListener((event)->finish());
        nextBtn.setOnClickListener((event)->{
            Intent outIntent=new Intent(MainActivity.this,ActivityTwo.class);
            outIntent.putExtra("title",titleText.getText().toString());
            outIntent.putExtra("body",bodyText.getText().toString());
            startActivity(outIntent);
        });
    }
}
