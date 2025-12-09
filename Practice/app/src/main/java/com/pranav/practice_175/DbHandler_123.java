package com.pranav.practice_175; // REPLACE WITH YOUR PACKAGE

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler_123 extends SQLiteOpenHelper {

    public DbHandler_123(Context context) {
        // DB Name can be anything, e.g., "ExamDB_123.db"
        super(context, "LoginDB_123.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Q6 Requirements: Timestamp, Location. We also add Username.
        db.execSQL("create table LoginTable_123(username TEXT, time TEXT, location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists LoginTable_123");
    }

    // Insert Method
    public Boolean insertLogin_123(String user, String time, String loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user);
        cv.put("time", time);
        cv.put("location", loc);

        long result = db.insert("LoginTable_123", null, cv);
        return result != -1;
    }

    // View Method
    public Cursor getHistory_123() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from LoginTable_123", null);
    }
}