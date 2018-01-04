package com.example.yahya.finalproject;

/**
 * Created by root on 04/01/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jason on 03/01/2018.
 */

public class AutoDatabaseHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = "AutoDatabaseHelper";

    public static final String DATABASE_NAME = "Gas_Database.db";
    public static final int VERSION_NUMBER = 2;
    public static final String TABLE_NAME = "Gas_Info";

    public static final String COL_ID = "Gas_ID";;
    public static final String COL_PRICE = "Price";
    public static final String COL_LITRES = "Litres";
    public static final String COL_KILOMETERS = "Kilometers";
    public static final String COL_DATE = "Date";

    public static final String CREATE_TABLE
            = "CREATE TABLE " + TABLE_NAME + " ( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_PRICE + " TEXT, "
            + COL_LITRES + " TEXT, "
            + COL_KILOMETERS + " TEXT, " // Make sure to put a comma back TEXT,
            + COL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP "
            + " );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public AutoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        Log.i(LOG_TAG, "Default Constructor called. Database created. ");
    }

    public Cursor getGasData() {
        Log.i(LOG_TAG, "In getGasData()");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return c;
    }

    public boolean updateGasData(String id, String price, String litres, String km) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String whereClause = "Gas_ID=?";

        contentValues.put(COL_ID, id);
        contentValues.put(COL_PRICE, price);
        contentValues.put(COL_LITRES, litres);
        contentValues.put(COL_KILOMETERS, km);
        db.update(TABLE_NAME, contentValues, whereClause,new String[] { id });
        return true;
    }

    public boolean insertGasData(String price, String litres, String km) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PRICE, price);
        contentValues.put(COL_LITRES, litres);
        contentValues.put(COL_KILOMETERS, km);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Integer deleteGasData (String row) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "Gas_ID=?";
        String[] whereArgs = new String[] { String.valueOf(row) };
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}