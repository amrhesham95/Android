package com.example.downloadimagethread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public static String link;
    EditText inputLinkText;
    ImageView imageView;
    Button downloadBtn;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputLinkText=findViewById(R.id.textFieldID);
        imageView=findViewById(R.id.imageViewID);
        downloadBtn=findViewById(R.id.btnDownloadID);
        link=inputLinkText.getText().toString();
        downloadBtn.setOnClickListener(v->{
            new MyAsyncTask().execute(link);
        });
    }

    public class MyAsyncTask extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url= null;
            InputStream inputStream = null;
            Bitmap bitmap = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
               inputStream= httpsURLConnection.getInputStream();
               bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            return bitmap;
        }
    }
}
