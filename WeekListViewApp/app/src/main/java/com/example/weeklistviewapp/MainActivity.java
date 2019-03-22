package com.example.weeklistviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DayBean[] days={
            new DayBean("bouquet","bouquet",R.drawable.bouquet),
            new DayBean("chicken"," katkot",R.drawable.chicken),
            new DayBean("syringe","sringa",R.drawable.syringe),
            new DayBean("money","flos",R.drawable.money),
            new DayBean("food","akl",R.drawable.food),
            new DayBean("home","beet",R.drawable.home),
            new DayBean("ambulance","esaaf",R.drawable.ambulance),

    };
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listViewID);
        listView.setAdapter(new DayAdapter(this,R.layout.row_layout,R.id.textViewID,days));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, days[position].getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
