package com.example.notedatastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NoteWelcomePage extends AppCompatActivity {
    Button nextBtn;
    Button closeBtn;
    EditText titleText;
    EditText bodyText;
    public static final String TITLE_KEY="title";
    public static final String BODY_KEY="body";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_welcome_page);
        nextBtn=(Button) findViewById(R.id.nextBtn);
        closeBtn=(Button) findViewById(R.id.closeBtn);
        titleText=(EditText) findViewById(R.id.textTitle);
        bodyText=(EditText) findViewById(R.id.textBody);
        closeBtn.setOnClickListener((event)->finish());
        nextBtn.setOnClickListener((event)->{
            Intent outIntent=new Intent(NoteWelcomePage.this,OptionPage.class);
            outIntent.putExtra(TITLE_KEY,titleText.getText().toString());
            outIntent.putExtra(BODY_KEY,bodyText.getText().toString());
            startActivity(outIntent);
        });
    }
}
