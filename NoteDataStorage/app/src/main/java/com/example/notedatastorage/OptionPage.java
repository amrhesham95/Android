package com.example.notedatastorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OptionPage extends AppCompatActivity {
    String SAVING_FILE_NAME="data";
    TextView titleText;
    TextView bodyText;
    Button saveInternalBtn;
    Button loadInternalBtn;
    Button saveSqlBtn;
    Button loadSqlBtn;
    Button closeBtn;
    SQLAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);
        db = new SQLAdapter(getApplicationContext());
        titleText= findViewById(R.id.textTitle);
        bodyText= findViewById(R.id.textBody);
        saveInternalBtn =findViewById(R.id.saveInternalButtonID);
        loadInternalBtn =findViewById(R.id.loadInternalButtonID);
        saveSqlBtn=findViewById(R.id.saveSqlButtonID);
        loadSqlBtn=findViewById(R.id.loadSqlButtonID);
        closeBtn=(Button) findViewById(R.id.closeBtn2);
        Intent incomingIntent=new Intent(getIntent());
        titleText.setText(incomingIntent.getStringExtra(NoteWelcomePage.TITLE_KEY));
        bodyText.setText(incomingIntent.getStringExtra(NoteWelcomePage.BODY_KEY));
        saveInternalBtn.setOnClickListener(v -> {
            try {
               DataOutputStream dataOutputStream = new DataOutputStream(openFileOutput(SAVING_FILE_NAME, Context.MODE_PRIVATE));
               dataOutputStream.writeUTF(titleText.getText().toString());
               dataOutputStream.writeUTF(bodyText.getText().toString());
               titleText.setText("");
               bodyText.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadInternalBtn.setOnClickListener(v -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(openFileInput(SAVING_FILE_NAME));
                titleText.setText(dataInputStream.readUTF());
                bodyText.setText(dataInputStream.readUTF());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        saveSqlBtn.setOnClickListener(v -> {
            NoteDTO note=new NoteDTO(titleText.getText().toString(),bodyText.getText().toString());
            db.insert(note);
            titleText.setText("");
            bodyText.setText("");

        });

        loadSqlBtn.setOnClickListener(v -> {
            String titleRecieved=incomingIntent.getStringExtra(NoteWelcomePage.TITLE_KEY);
            NoteDTO note=db.retrieve(titleRecieved);
            titleText.setText(note.getTitle());
            bodyText.setText(note.getBody());

        });
        closeBtn.setOnClickListener((event)->finish());
    }
}
