package com.example.notedatastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLAdapter {

    Context context;


    public SQLAdapter(Context context) {
        this.context=context;
    }
    public void insert(NoteDTO note){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Helper.TITLE,note.getTitle());
        contentValues.put(Helper.BODY,note.getBody());
        Helper dbHelper=new Helper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(Helper.TABLE_NAME,null,contentValues);
    }

    public NoteDTO retrieve(String title){
        NoteDTO note=new NoteDTO();
        ContentValues contentValues=new ContentValues();
        contentValues.getAsString(title);
        Helper dbHelper=new Helper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Cursor cursor=db.rawQuery("SELECT * FROM "+Helper.TABLE_NAME+" where "+Helper.TITLE+" like '"+title+"'",null);
        Cursor cursor=db.rawQuery("SELECT * FROM "+Helper.TABLE_NAME+" where "+Helper.TITLE+"=?",new String[]{title});
        while (cursor.moveToNext()){
            note.setTitle(cursor.getString(0));
            note.setBody(cursor.getString(1));
        }
        return  note;
    }







    private static class Helper extends SQLiteOpenHelper{
        private static String DATABASE_NAME = "mydb";
        private static int DATABASE_VERSION = 1;
        private static String TABLE_NAME = "MYTABLE";
        private static String TITLE = "TITLE";
        private static String BODY = "BODY";
        private Helper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            String createQuery = "CREATE TABLE " + TABLE_NAME + "("
                    + TITLE + " VARCHAR(255) PRIMARY KEY," + BODY + " TEXT" + ")";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
