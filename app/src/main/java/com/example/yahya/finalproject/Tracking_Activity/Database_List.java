package com.example.yahya.finalproject.Tracking_Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.yahya.finalproject.R;

public class Database_List extends Activity {


    DATABASEAdapter myDB;
    ListView listview;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database__list);
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        listview = (ListView) findViewById(R.id.database_list);

        String [] columns = new String[] {  DATABASEAdapter.KEY_ID,
                DATABASEAdapter.CREATED_AT,
                DATABASEAdapter.KEY_ACTIVITY,
                DATABASEAdapter.KEY_TIME,
                DATABASEAdapter.KEY_COMMENT   };

        int[] to = new int[] {
                R.id.id_list,
                R.id.time_list,
                R.id.activity_list,
                R.id.duration_list,
                R.id.comment_list
        };

        Toast.makeText(this,"opening database and retrieving data",Toast.LENGTH_SHORT).show();

        myDB = new DATABASEAdapter(this);
        myDB.open();

        cursor = myDB.getAllRows();

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.database_list_entry, cursor, columns, to,0);

        listview.setAdapter(simpleCursorAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Bundle info = new Bundle();
                info.putString("KEY_ID", Integer.toString(position+1));

                Log.i("Data click", "at position: "+position);

                Intent myIntnet = new Intent(getApplicationContext(), EditItem.class);
                myIntnet.putExtras(info);
                startActivity(myIntnet);
                finish();

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cursor != null) {

            cursor.close();
        }
        if (myDB != null) {
            myDB.close();
        }
        final String ACTIVITY_NAME = "DatabaseListActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
