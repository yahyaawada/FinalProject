package com.example.yahya.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ThermostatActivity extends Activity {



    private Button saveButton, viewData;
    private EditText tempText, dateText, timeText;
    DatabaseHelperPhil mDatabaseHelper;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

         lv = (ListView)findViewById(R.id.list_results);
        //lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));

       // button = (Button) findViewById(R.id.buttonTest);

        saveButton = (Button) findViewById(R.id.insert_button);
        viewData = (Button) findViewById(R.id.saveNew);

        dateText = (EditText) findViewById(R.id.dateText);
        timeText = (EditText) findViewById(R.id.timeText);
        tempText = (EditText) findViewById(R.id.tempText);
        mDatabaseHelper = new DatabaseHelperPhil(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  newEntry= dateText.getText().toString();
                String newEntry3 = tempText.getText().toString();
                String newEntry2 = timeText.getText().toString();
            if(dateText.length()!=0 && timeText.length() != 0 && tempText.length() != 0){ /**&& tempText.length()!=0*/
                //AddData(newEntry);
                mDatabaseHelper.addData(newEntry, newEntry2, newEntry3);
                dateText.setText("");
                timeText.setText("");
                tempText.setText("");
            }else{
                toastMessage("You must input something!");
            }

            }
            //populateListView();
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThermostatActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });



    }

//    public void AddData(String newEntry){
//        boolean  insertData = mDatabaseHelper.addData(newEntry);
//
//        if(insertData==true){
//            toastMessage("data successfully stored in db");
//        }else{
//            toastMessage("Data NOT stored!!!");
//        }
//
//    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}