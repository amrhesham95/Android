package com.example.fragmentbackstack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button addButton1;
    Button addButton2;
    Button addButton3;
    Button replaceButton;
    Button removeButton;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton1=findViewById(R.id.addButtonID_1);
        addButton2=findViewById(R.id.addButtonID_2);
        addButton3=findViewById(R.id.addButtonID_3);
        replaceButton=findViewById(R.id.replaceButtonID);
        removeButton=findViewById(R.id.removeButtonID);
        addButton1.setOnClickListener(v -> {
            firstFragment firstFragment=new firstFragment();
            addFragment(firstFragment,"firstFragment");
        });
        addButton2.setOnClickListener(v -> {
            secondFragment secondFragment=new secondFragment();
            addFragment(secondFragment,"secondFragment");
        });
        addButton3.setOnClickListener(v -> {
            thirdFragment thirdFragment=new thirdFragment();
            addFragment(thirdFragment,"thirdFragment");
        });
        replaceButton.setOnClickListener(v -> {
            firstFragment firstFragment=new firstFragment();

            fragmentManager=getSupportFragmentManager();
            fragmentManager.popBackStackImmediate();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.insertLayoutID,firstFragment,"firstFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        removeButton.setOnClickListener(v -> {
            fragmentManager=getSupportFragmentManager();
            fragmentManager.popBackStackImmediate();

        });
    }
    public void addFragment(Fragment fragment,String tag){

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.insertLayoutID,fragment,tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
