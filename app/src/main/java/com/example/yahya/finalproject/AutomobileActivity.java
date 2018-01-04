package com.example.yahya.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AutomobileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);

        //Creating an array adapter for the listView
        ListView lv = (ListView)findViewById(R.id.list_results);

    }
}
