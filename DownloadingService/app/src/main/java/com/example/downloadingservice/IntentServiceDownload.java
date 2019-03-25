package com.example.downloadingservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class IntentServiceDownload extends IntentService {
    private final static String FILENAME="picture";
    MyReceiver receiver;
    public IntentServiceDownload() {
        super("IntentServiceDownload");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        URL url= null;
        InputStream inputStream = null;
        try {
            url = new URL(intent.getStringExtra("picURL"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            IntentFilter intentFilter=new IntentFilter("fileDownloaded");
            receiver=new MyReceiver();
            registerReceiver(receiver,intentFilter);

            HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
            inputStream=httpsURLConnection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            FileOutputStream fileOutputStream=openFileOutput(FILENAME, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,0,fileOutputStream);
            Intent sendIntent=new Intent();
            sendIntent.putExtra("fileName",FILENAME);
            sendIntent.setAction("fileDownloaded");
            //sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendBroadcast(sendIntent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
