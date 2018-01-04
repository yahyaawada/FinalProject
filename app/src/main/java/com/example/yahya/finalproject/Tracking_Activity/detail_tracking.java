package com.example.yahya.finalproject.Tracking_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yahya.finalproject.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class detail_tracking extends Activity {

    DATABASEAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final String ACTIVITY_NAME = "DetailActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String activity = bundle.getString("SearchText");

        Toast.makeText(this,"Called By Tracking Activity at position: "+activity, Toast.LENGTH_LONG).show();

        final EditText activityName = (EditText)findViewById(R.id.activity);
        final EditText duration = (EditText)findViewById(R.id.duration);
        final EditText comment = (EditText)findViewById(R.id.comment);
        Button insert_button = (Button)findViewById(R.id.insert_fragment);

        activityName.setText(activity);

        //Create an object for opening a database:
        myDB = new DATABASEAdapter(this);
        myDB.open();

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = activityName.getText().toString();
                String time = duration.getText().toString();
                String comm = comment.getText().toString();

                //getting current date and time using Date class
//                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                Date dateobj = new Date();
                String timestamp = df.format(dateobj);

                myDB.insertRow(timestamp, name, time, comm);

                final Toast toast = Toast.makeText(detail_tracking.this , "new data added to database", Toast.LENGTH_LONG);
                Log.i("Database", "new data added to database from fragment");
                toast.show();
                finish();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onRestart(){
        super.onRestart();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }

    protected void onResume(){
        super.onResume();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onPause(){
        super.onPause();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        final String ACTIVITY_NAME = "DetailTrackingActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
