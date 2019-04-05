package com.example.bluetoothdemo;

import android.bluetooth.BluetoothDevice;

public class MyBluetoothDevice {
    BluetoothDevice myBluetoothDevice;
    String name;

    @Override
    public String toString() {
        return name;
    }


    public BluetoothDevice getMyBluetoothDevice() {
        return myBluetoothDevice;
    }

    public void setMyBluetoothDevice(BluetoothDevice myBluetoothDevice) {
        this.myBluetoothDevice = myBluetoothDevice;
        name=myBluetoothDevice.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyBluetoothDevice(BluetoothDevice myBluetoothDevice) {
        this.myBluetoothDevice = myBluetoothDevice;
        this.name=myBluetoothDevice.getName();
    }


}
