package com.example.downloadingservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button downloadButton;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadButton=findViewById(R.id.btn_download);
        editText=findViewById(R.id.textFieldID);
        downloadButton.setOnClickListener(v -> {
            Intent downloadIntent =new Intent(this,IntentServiceDownload.class);
            downloadIntent.putExtra("picURL",editText.getText().toString());
            startService(downloadIntent);
        });
    }
}
