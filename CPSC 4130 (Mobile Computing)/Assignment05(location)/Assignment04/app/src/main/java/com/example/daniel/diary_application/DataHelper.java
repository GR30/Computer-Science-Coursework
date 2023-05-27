package com.example.daniel.diary_application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "diary.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table diary(" +
                "dt datetime null, " +
                "title text null, " +
                "content text null);";
        Log.d("Diary", "onCreate: " + sql);
        db.execSQL(sql);
        String sql2 = "create table account(username text, password text, email text);";
        Log.d("Data Entry", "onCreate: " + sql2);
        db.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub
    }
}
