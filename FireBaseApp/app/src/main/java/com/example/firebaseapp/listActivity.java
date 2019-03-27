package com.example.firebaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listActivity extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    ArrayList<String> values;
    ArrayAdapter <Message>adapter;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=findViewById(R.id.listViewID);
        database= FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        myRef = database.getReference(firebaseAuth.getCurrentUser().getUid());
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent=new Intent(this,DetailsActivity.class);
            intent.putExtra("body",adapter.getItem(position).getBody());
            startActivity(intent);
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                adapter.clear();
                //String value = dataSnapshot.getValue(String.class);
                for(DataSnapshot dataSnapshotItr: dataSnapshot.getChildren()){
                    adapter.add(dataSnapshotItr.getValue(Message.class));
                }
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
