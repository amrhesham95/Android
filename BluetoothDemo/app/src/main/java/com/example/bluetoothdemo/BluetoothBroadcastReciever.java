package com.example.bluetoothdemo;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice device =intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if(BluetoothDevice.ACTION_FOUND.equals(action)){
            Toast.makeText(context, device.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
