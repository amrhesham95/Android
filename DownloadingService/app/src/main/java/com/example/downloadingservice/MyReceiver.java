package com.example.downloadingservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent secondActivityIntent=new Intent(context,SecondActivity.class);
        secondActivityIntent.putExtra("fileName",intent.getStringExtra("fileName"));
        context.startActivity(secondActivityIntent);
    }
}
