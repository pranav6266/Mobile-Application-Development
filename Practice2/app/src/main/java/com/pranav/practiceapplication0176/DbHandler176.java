package com.pranav.practiceapplication0176;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler176 extends SQLiteOpenHelper {
    public DbHandler176(Context context) {
        super(context, "MyDb176.db" , null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table MyTable(name TEXT, roll_no TEXT, dept TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertData(String name, String roll_no, String dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("roll_no", roll_no);
        cv.put("dept", dept);
        long result = db.insert("MyTable", null, cv);
        return result != -1;
    }

    public Cursor getAllData_176(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from MyTable", null);
    }
}
