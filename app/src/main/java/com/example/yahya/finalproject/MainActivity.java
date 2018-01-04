package com.example.yahya.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    int i = 0;
    String [] sourceData = { "Activity Tracking", "Nutrition Activity ", "ArrayList Activity (tb removed)",
            "House Thermostat", "Automobile Activity"};

    protected final String NAME = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        //Open the sharedPreferences file:
        final SharedPreferences sPrefs = getSharedPreferences("FinalProject", Context.MODE_PRIVATE);

        //Seeing what items are in the sharedPreferences file:
        String fromFile = sPrefs.getString("Name", "Nothing found");
        fromFile = sPrefs.getString("Not there", "Nothing found");

        //Creating an array adapter for the listView
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextPage;

                switch(position) {
                    case 0://Activity one
                        //Edit the file:
                        SharedPreferences.Editor writer = sPrefs.edit();
                        //Add entries to the file:
                        writer.putString("Name", "FinalProject");

                        //this writes the file:
                        writer.commit();

                        //For passing information to the next Activity, create a bundle:
                        Bundle someInformation = new Bundle();
                        nextPage = new Intent(MainActivity.this, ActivityTracking.class);

                        //To transition to the new Activity  with requestCode 5 and pass someInformation:
                        startActivityForResult(nextPage, 5, someInformation);
                        break;

                    case 1://Activity two:
                        nextPage = new Intent(MainActivity.this, NutritionActivity.class);
                        startActivityForResult(nextPage, 6);
                        break;
                    case 2: // this is just the arraylist activity
                        // I think we should remove this
                        startActivity(new Intent(MainActivity.this, ArrayListActivity.class));
                        break;
                    case 3://SQL EXAMPLE
                        startActivity(new Intent(MainActivity.this, ThermostatActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, AutomobileActivity.class));
                        break;
                }
            }
        });

        //Set a logging message at the Error level
        Log.e(NAME, "In onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
