package com.example.yahya.finalproject.Tracking_Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yahya on 2018-01-01.
 */

public class DATABASEAdapter {

    private static final String TAG = "DATABASEAdapter";

    // column names
    public static final  String KEY_ID = "_id";
    public static final  String CREATED_AT = "timeStamp";
    public static final  String KEY_ACTIVITY = "ACTIVITY";
    public static final  String KEY_TIME = "TIME";
    public static final  String KEY_COMMENT = "COMMENT";

    public static final String[] ALL_KEYS = new String[] {KEY_ID, CREATED_AT, KEY_ACTIVITY, KEY_TIME, KEY_COMMENT};

    //Colomn numbers for each field name
    public static final int COL_ROWID = 0;
    public static final int COL_CREATED_AT = 1;
    public static final int COL_ACTIVITY = 2;
    public static final int COL_TIME = 3;
    public static final int COL_COMMENT = 4;

    // Definitions: Database name, table name, version
    private static final String DATABASE_NAME = "ActivityDatabase.db" ;
    private static final String TRACKING_TABLE = "ACTIVITY_TABLE" ;
    private static final int DATABASE_VERSION = 1 ;

    //SQL statement to create database
    private static final String DATABASE_CREATE_SQL =
            "CREATE TABLE " + TRACKING_TABLE
                    + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_ACTIVITY + " TEXT, "
                    + CREATED_AT + " TEXT, "
                    + KEY_TIME + " INTEGER, "
                    + KEY_COMMENT + " TEXT"
                    + ");";
//CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "

    private final Context context;
    private DatabaseHelper myDbHelper;
    private SQLiteDatabase db;

    public DATABASEAdapter(Context ctx){
        this.context = ctx;
        myDbHelper = new DatabaseHelper(context);
    }

    //Open the database connection
    public DATABASEAdapter open(){
            db = myDbHelper.getWritableDatabase();
        return this;
    }

    //Close the database connection
    public void close(){
        myDbHelper.close();
    }

    //Add a new set of values to be inserted into  the database
    public long insertRow(String time, String activity, String duration, String comment){
        ContentValues initialValues = new ContentValues();
        initialValues.put(CREATED_AT,time);
        initialValues.put(KEY_ACTIVITY, activity);
        initialValues.put(KEY_TIME, duration);
        initialValues.put(KEY_COMMENT, comment);

        //Insert the data into the database
        return db.insert(TRACKING_TABLE, null, initialValues);
    }

    //Delete a row from the database, rowId (primary key)
    public boolean deleteRow(String rowId){
        String where = KEY_ID + "=" + rowId;
        return db.delete(TRACKING_TABLE, where, null) != 0;
    }

    //Delete the database
    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ID);
        if(c.moveToFirst()){
            do{
                deleteRow(c.getString((int) rowId));
            } while (c.moveToNext());
        }
        c.close();
//
//        //Destroy old database:
//        db.execSQL("DROP TABLE IF EXISTS " + TRACKING_TABLE);
//        //Recreate new database:
//        myDbHelper.onCreate(db);
    }

    //Return all data in the database
    public Cursor getAllRows(){
        Cursor c = db.query(TRACKING_TABLE, ALL_KEYS, null, null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }

    //get one row
    public Cursor getRow(String id){
        Cursor c = db.query (TRACKING_TABLE, ALL_KEYS, KEY_ID+" = ?", new String[]{id}, null, null, null) ;

        return c;
    }

    //Change an existing row to be equal to new data
    public boolean updateRow(String rowId, String activity, String duration, String comment){
        String where = KEY_ID + "=" + rowId;
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_ACTIVITY, activity);
        newValues.put(KEY_TIME, duration);
        newValues.put(KEY_COMMENT, comment);

        //Insert it into the database
        return db.update(TRACKING_TABLE, newValues, where, null) != 0;
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db){
            _db.execSQL(DATABASE_CREATE_SQL);
            Log.i ("database", " table Created--------------------") ;
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading application's database from version" + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            //Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + TRACKING_TABLE);

            //Recreate new database:
            onCreate(_db);
        }
    }
}

