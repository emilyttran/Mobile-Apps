package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emily_000 on 3/7/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // name the database
    public static final String DATABASE_NAME = "Student.db";

    // name the table
    public static final String TABLE_NAME = "student_table";

    // create the column names
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRSTNAME";
    public static final String COL_3 = "LASTNAME";
    public static final String COL_4 = "GRADE";

    // I want to save this table into a file named student.db
    // HOW THIS LOOKS:
    // within the file, you have the table, within the table, you have the columns

    // calling constructor from the parent class
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // creating the table with the columns and their data type
        // HOW? SQL statement
        sqLiteDatabase.execSQL("create table " + TABLE_NAME
                + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, GRADE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // i -> old version, i1 -> new version
        // if the table exists, you delete the table and then create a new one

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // INSERT ROW =================================================================================
    // write a method that returns true if the data has been successfully inserted into the table
    public boolean insertData(String firstName, String lastName, String grade){
        // get database you want to insert into
        SQLiteDatabase db = this.getWritableDatabase();

        // get the content value
        ContentValues cv = new ContentValues();

        // put the value into each column
        cv.put(COL_2,firstName);
        cv.put(COL_3, lastName);
        cv.put(COL_4, grade);       // like hash tables

        // call insert method
        long result = db.insert(TABLE_NAME,null,cv);      // -1 means unsuccessful

        // if it is correct, return true
        return result != -1;
    }

    // UPDATE ROW =================================================================================
    public boolean updateData(String id, String firstName, String lastName, String grade){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COL_1, id);
            cv.put(COL_2, firstName);
            cv.put(COL_3, lastName);
            cv.put(COL_4, grade);

            db.update(TABLE_NAME, cv,"ID = ?", new String[]{id});
            return true;
    }

    // SHOW ROWS ===================================================================================
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null); // select everything from table to display
        return res;
    }
}
