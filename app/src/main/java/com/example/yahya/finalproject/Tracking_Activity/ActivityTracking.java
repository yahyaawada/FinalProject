package com.example.yahya.finalproject.Tracking_Activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yahya.finalproject.R;


public class ActivityTracking extends Activity{

    public static String [] sourceData = { "Running","Walking", "Biking", "Swimming",
            "Skating"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

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
                Intent startIntent = new Intent(getApplicationContext(), TrackingDatabaseResults.class);
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
}
