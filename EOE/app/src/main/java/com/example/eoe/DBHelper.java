package com.example.eoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User(fullname TEXT primary key, amka TEXT, email TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean insert(String fullname,String amka, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", fullname);
        contentValues.put("amka", amka);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long result = db.insert("User", null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean update(String fullname,String amka, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amka", amka);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        Cursor cursor = db.rawQuery("Select * from User where fullname = ?", new String[] {fullname});

        if(cursor.getCount() > 0){
            long result = db.update("User", contentValues, "fullname=?", new String[] {fullname});
            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select * from User", null);

        if(cursor.getCount() > 0){
            long result = db.delete("User", null,null);
            if(result == -1){
                return false;
            }else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select * from User", null);
        return cursor;
    }
}
