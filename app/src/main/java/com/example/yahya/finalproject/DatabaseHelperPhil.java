package com.example.yahya.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 01/01/18.
 */



public class DatabaseHelperPhil extends SQLiteOpenHelper {

    private static final String TAG =  "DataBase Helper";

    public static final String DATABASE_NAME = "users.db";
    private static final String TABLE_NAME = "temperature_table";
    private static final String COL1= "ID";
    private static final String COL2= "Date";
    private static final String COL3="Time";
    private static final String COL4= "Temperature";






    public DatabaseHelperPhil(Context context) {
        super(context, DATABASE_NAME, null,3);
    }


//    public DatabaseHelperPhil(Context context) {
//        super(context, TABLE_NAME, null,1);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE "+ TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 + " TEXT,"+ COL3 + " TEXT," +COL4 +
            " TEXT);";

      //  String createTable = "CREATE TABLE "+ TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 + " TEXT);";//+ COL3 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME +";");
        onCreate(db);
    }

    public boolean addData(String item, String item2, String item3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, item);
        cv.put(COL3, item2);
        cv.put(COL4, item3);
        Log.d( TAG, "addData : Adding " + item + " to database "+ TABLE_NAME);

        Long result = db.insert(TABLE_NAME, null, cv);

        // if data is put in database incorrectly, shluld return -1

        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
         String query = "SELECT * FROM "+ TABLE_NAME;
         Cursor data = db.rawQuery(query,null);
         return data;
    }

    public Cursor getItemID(String date, String time, String temperature){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + date + "'" + " AND " + COL3 + " = '"
                + time +"'" + " AND " + COL4 + " = '" + temperature + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateDate(String newDate, int id, String oldDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newDate + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldDate + "'";
        //ideally do logs here when you have time
        db.execSQL(query);
    }

    public void deleteDate(int id, String date){
        SQLiteDatabase db =     this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME + " WHERE "+ COL1 + " = '" + id + "'" + " AND " + COL2
                + " = '" + date + "'";

        //logs.

        db.execSQL(query);
     }

    public void delete_byID(int id){


        SQLiteDatabase db =     this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME + " WHERE "+ COL1 + " = '" + id + "'" ;
        db.execSQL(query);
        //db.execSQL("VACUUM "+ TABLE_NAME ";");
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");

    }

}
