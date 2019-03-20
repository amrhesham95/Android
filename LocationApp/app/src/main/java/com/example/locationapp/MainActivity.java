package com.example.locationapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button btnGetLocation;
    Button btnSOS;
    Button btnShowMap;
    LocationManager locationManager;
    MyLocationListener locationListener;
    Location myLocationGlobal;
    String smsLocation;
    private static final int PERM_REQ=0;
    List<Address> addressList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetLocation = findViewById(R.id.btnGetLocationID);
        btnSOS=findViewById(R.id.btnSOSID);
        btnShowMap=findViewById(R.id.btnShowMapID);
        locationListener=new MyLocationListener();
        btnGetLocation.setOnClickListener((v) -> {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERM_REQ);


            }
            else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        });
        btnSOS.setOnClickListener(v -> {
            Geocoder geocoder=new Geocoder(getApplicationContext());
            try {
                if(myLocationGlobal!=null){
                    addressList= geocoder.getFromLocation(myLocationGlobal.getLatitude(),myLocationGlobal.getLongitude(),1);
                    smsLocation=addressList.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.parse("smsto:01116243075");
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO,uri);
            smsIntent.putExtra("address"  , "0123456789");
            smsIntent.putExtra("sms_body"  , smsLocation);
            startActivity(smsIntent);
        });
        btnShowMap.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,GoogleMapsActivity.class);
            startActivity(intent);

        });

    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(getApplicationContext(), "Longitude:"+location.getLongitude()+"\n"+"latitude:"+location.getLatitude(), Toast.LENGTH_SHORT).show();
            myLocationGlobal=location;
            locationManager.removeUpdates(locationListener);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERM_REQ);

        }

        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

}
