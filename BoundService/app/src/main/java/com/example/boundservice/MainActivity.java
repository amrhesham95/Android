package com.example.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BoundService myService;
    boolean isBound=false;
    TextView textView;
    Button btnShowDate;
    private ServiceConnection myConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyLocalBinder binder= (BoundService.MyLocalBinder) service;
            myService  = binder.getService();
            isBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        btnShowDate=findViewById(R.id.button);
        Intent intent=new Intent(this,BoundService.class);
        bindService(intent,myConnection, Context.BIND_AUTO_CREATE);

        btnShowDate.setOnClickListener(v -> {
            textView.setText(myService.getCurrentTime());
        });
    }
}
