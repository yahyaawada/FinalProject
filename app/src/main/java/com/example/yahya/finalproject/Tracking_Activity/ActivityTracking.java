package com.example.yahya.finalproject.Tracking_Activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yahya.finalproject.R;


public class ActivityTracking extends AppCompatActivity{

    public static String [] sourceData = { "Running","Walking", "Biking", "Swimming",
            "Skating"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tracking_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tracking Activity, Yahya Awada 2018", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Creating an array adapter for the listView
        final ListView lv = (ListView)findViewById(R.id.tracking_list);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            tracking_fragment newFragment = new tracking_fragment();
            Bundle args = new Bundle();
            args.putInt("position", 0);
            newFragment.setArguments(args);                                         // (1) Communicate with Fragment using Bundle
            FragmentTransaction ft = getFragmentManager().beginTransaction();       // begin  FragmentTransaction
            ft.add(R.id.frame, newFragment);                                        // add    Fragment
            ft.commit();                                                            // commit FragmentTransaction
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    onItemSelected(position); // Communicate with Activity
                }else{

                    String activity = (String) lv.getItemAtPosition(position);
                    Intent myIntent = new Intent(ActivityTracking.this,detail_tracking.class);
                    myIntent.putExtra("SearchText", activity);
                    startActivity(myIntent);
                }

            }
        });

        // launch weather forecast window
        Button button3 = (Button)findViewById(R.id.check_weather);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                startActivity(startIntent);
            }
        });

        // view database results
        Button databaseResults = (Button)findViewById(R.id.view_results);
        databaseResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), Database_List.class);
                startActivity(startIntent);
            }
        });
    }

    public void onItemSelected(int position) {

        Toast.makeText(this, "Called By Tracking Activity: position - "+ position, Toast.LENGTH_SHORT).show();
        // Load tracking Fragment
        tracking_fragment secondFragment = new tracking_fragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        secondFragment.setArguments(args);                                                           // Communicate with Fragment using Bundle
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getFragmentManager().beginTransaction().replace(R.id.frame, secondFragment).commit();    // replace frame
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.tracking_menu, m );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi){

        int id = mi.getItemId();

        switch (id){
            // selecting the first icon
            case R.id.action_one:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Yahya Awada, version 1.0");
                builder.setMessage("-Click on an activity to add a new log\n" +
                                    "-View previous logs\n" +
                                    "-Update the data list or delete logs\n" +
                                     "-Check the current weather");

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }

    protected void onStart(){
        super.onStart();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onRestart(){
        super.onRestart();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }

    protected void onResume(){
        super.onResume();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onPause(){
        super.onPause();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        final String ACTIVITY_NAME = "TrackingActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
