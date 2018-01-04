package com.example.yahya.finalproject.Tracking_Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yahya.finalproject.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by yahya on 2017-12-12.
 */

public class tracking_fragment extends Fragment{

    int position = 0;
    EditText activityName, duration, comment;
    Button insert;
    DATABASEAdapter myDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.activity_detail, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set values for view here
        activityName = (EditText)view.findViewById(R.id.activity);
        duration = (EditText)view.findViewById(R.id.duration);
        comment = (EditText)view.findViewById(R.id.comment);

        insert = (Button)view.findViewById(R.id.insert_fragment);

        //Create an object for opening a database:
        myDB = new DATABASEAdapter(getActivity());
        myDB.open();

        // update view
        activityName.setText(ActivityTracking.sourceData[position]);

        insert.setOnClickListener(new View.OnClickListener() {
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

                Toast.makeText(getActivity(), "new data added to database from fragment", Toast.LENGTH_LONG).show();
                Log.i("Database", "new data added to database from fragment");

                duration.setText(" ");
                comment.setText(" ");
            }
        });
    }


    @Override
    public void onStart(){
        super.onStart();
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    public void onPause(){
        super.onPause();
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    public void onStop(){
        super.onStop();
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDB != null) {
            myDB.close();
        }
        final String ACTIVITY_NAME = "FragmentActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}
