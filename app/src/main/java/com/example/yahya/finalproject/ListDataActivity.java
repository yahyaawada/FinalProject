package com.example.yahya.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ListDataActivity extends Activity {

    DatabaseHelperPhil mDatabaseHelper;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        mDatabaseHelper = new DatabaseHelperPhil(this);
        mListView = (ListView) findViewById(R.id.listView2);

        populateListView();


    }



    public void populateListView(){
        Log.d(TAG, "populating the listview with the data");


        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
            listData.add(data.getString(3));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);

        //populateListView();

        // onclick to set data into listview

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String date = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
                String time = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
                String temperature = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
                Log.d(TAG, "onItemClicked: you clicked on " + date);

                Cursor data = mDatabaseHelper.getItemID(date,time,temperature);
                int itemID = -1;

                while(data.moveToNext()){

                    itemID = data.getInt(0);



                }

                if(itemID>-1){
                    Log.d(TAG, "onItemClick: the ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("date", date);
                    editScreenIntent.putExtra("time", time);
                    editScreenIntent.putExtra("temperature", temperature);
                    startActivity(editScreenIntent);

                }else{
                    toastMessage("no ID associated with that Date");
                }

            }
        });
    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
