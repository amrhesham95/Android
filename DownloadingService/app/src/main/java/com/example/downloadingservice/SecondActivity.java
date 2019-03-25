package com.example.downloadingservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SecondActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView=findViewById(R.id.imageView);
        try {
            FileInputStream fileInputStream= openFileInput(getIntent().getStringExtra("fileName"));
            imageView.setImageBitmap(BitmapFactory.decodeStream(fileInputStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
