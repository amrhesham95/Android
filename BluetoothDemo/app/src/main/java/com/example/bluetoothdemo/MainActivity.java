package com.example.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    Button enableBtn;
    Button disableBtn;
    Button pairedBtn;
    ArrayAdapter<MyBluetoothDevice> arrayAdapter;
    ListView listView;
    Set<MyBluetoothDevice> pairedDevices;
    Set<BluetoothDevice> bluetoothDevices;
    BluetoothServerSocket bluetoothServerSocket;
    private Handler handler;
    private final static String SERVICE_NAME = "ServerService";
    private final static String UUID_STRING = "d5acad30-53cf-11e9-b475-0800200c9a66";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, String.valueOf(bluetoothAdapter.getState()), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(new BluetoothBroadcastReciever());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pairedDevices = new HashSet<>();
        bluetoothManager = (BluetoothManager) getSystemService(getApplicationContext().BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        enableBtn = findViewById(R.id.enableBtnID);
        disableBtn = findViewById(R.id.disableBtnID);
        pairedBtn = findViewById(R.id.pairedBtnID);
        listView = findViewById(R.id.listViewID);
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(new BluetoothBroadcastReciever(), intentFilter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

        });
        enableBtn.setOnClickListener((event) -> {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
            bluetoothAdapter.startDiscovery();
            bluetoothDevices = bluetoothAdapter.getBondedDevices();
            if (bluetoothDevices.size() > 0) {
                for (BluetoothDevice device : bluetoothDevices) {
                    MyBluetoothDevice myBluetoothDevice = new MyBluetoothDevice(device);
                    pairedDevices.add(myBluetoothDevice);
                }
            }


        });

        disableBtn.setOnClickListener((event) -> {
            bluetoothAdapter.disable();
            unregisterReceiver(new BluetoothBroadcastReciever());
        });
        pairedBtn.setOnClickListener((event) -> {
            if (pairedDevices.size() > 0) {
                for (MyBluetoothDevice device : pairedDevices) {
                    arrayAdapter.add(device);

                }

            }
        });


    }

    private class AcceptThread extends Thread {


        private final BluetoothServerSocket mmServerSocket;
        public void manageMyConnectedSocket(BluetoothSocket bluetoothSocket){

        }
        private AcceptThread() {
            BluetoothServerSocket temp = null;
            try {
                temp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(SERVICE_NAME, UUID.fromString(UUID_STRING));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmServerSocket = temp;
        }

        @Override
        public void run() {
            BluetoothSocket bluetoothSocket = null;
            while (true) {
                try {
                    bluetoothSocket = mmServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(bluetoothSocket!=null){
                    manageMyConnectedSocket(bluetoothSocket);
                }
            }
        }
    }

    private class ConnectedThread extends Thread{
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        private byte[] mmBuffer;
        public ConnectedThread(BluetoothSocket socket){
            mmSocket = socket;
            OutputStream tempOutStream=null;
            InputStream tempInputStream=null;
            try {
               tempInputStream=mmSocket.getInputStream();
               tempOutStream=mmSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mmInStream=tempInputStream;
            mmOutStream=tempOutStream;
        }
        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    // Send the obtained bytes to the UI activity.
                    Message readMsg = handler.obtainMessage(
                            MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer);
                    readMsg.sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
    }
}
