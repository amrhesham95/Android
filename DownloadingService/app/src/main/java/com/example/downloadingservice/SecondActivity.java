package com.example.downloadingservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SecondActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type.startsWith("image/")) {
            //Toast.makeText(this, intent.getParcelableExtra(Intent.EXTRA_STREAM).toString(), Toast.LENGTH_LONG).show();
            Uri imageURI=(Uri)intent.getParcelableExtra(Intent.EXTRA_STREAM);
            imageView.setImageURI(imageURI);
        } else {
            try {

                FileInputStream fileInputStream = openFileInput(getIntent().getStringExtra("fileName"));
                imageView.setImageBitmap(BitmapFactory.decodeStream(fileInputStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
