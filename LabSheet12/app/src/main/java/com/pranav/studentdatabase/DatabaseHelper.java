package com.pranav.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Students.db";
    private static final int DATABASE_VERSION = 1;
    // Table and Column Names
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_ID = "student_id";
    private static final String COLUMN_NAME = "student_name";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    // --- CRUD Operations ---
    public boolean addStudent(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ID, id);

        long result = db.insert(TABLE_STUDENTS, null, cv);
        db.close();
        return result != -1;
    }
    public boolean deleteStudent(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_STUDENTS, COLUMN_ID + " = ?", new String[]{id});
        db.close();
        return result > 0;
    }
    public String getAllStudents() {
        StringBuilder stringBuilder = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                stringBuilder.append("ID: ").append(id).append(", Name: ").append(name).append("\n");
            } while (cursor.moveToNext());
        } else {
            stringBuilder.append("No students found in the database.");
        }
        cursor.close();
        db.close();
        return stringBuilder.toString();
    }
}

