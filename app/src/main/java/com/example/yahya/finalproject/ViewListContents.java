package com.example.yahya.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 04/01/18.
 */

public class ViewListContents extends Activity{

    DatabaseHelperPhil myDB;
    ArrayList<Save> userList;
    ListView listView;
    Save user;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contents_layout);

        myDB = new DatabaseHelperPhil(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getData();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ViewListContents.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                user = new Save(data.getString(1), data.getString(2), data.getString(3));
                userList.add(i, user);
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3));
                System.out.println(userList.get(i).getDate());
                i++;
            }
            ArrayListAdapter adapter = new ArrayListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    toastMessage("hello");
                    Intent editScreenIntent = new Intent(ViewListContents.this, EditDataActivity.class);
                    startActivity(editScreenIntent);
                }
            });
        }
    }

//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editScreenIntent = new Intent(ViewListContents.this, EditDataActivity.class);
//                startActivity(editScreenIntent);
//            }
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//                String date = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
//                String time = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
//                String temperature = adapterView.getItemAtPosition(i).toString();  // may need to use STring and .toString();
//                Log.d(TAG, "onItemClicked: you clicked on " + date);
//
//                Cursor data = myDB.getItemID(date,time,temperature);
//                int itemID = -1;
//
//                while(data.moveToNext()){
//
//                    itemID = data.getInt(0);
//
//
//
//                }
//
//                if(itemID>-1){
//                    Log.d(TAG, "onItemClick: the ID is: " + itemID);
 //                   Intent editScreenIntent = new Intent(ViewListContents.this, EditDataActivity.class);
//                    editScreenIntent.putExtra("id", itemID);
//                    editScreenIntent.putExtra("date", date);
//                    editScreenIntent.putExtra("time", time);
//                    editScreenIntent.putExtra("temperature", temperature);
  //                  startActivity(editScreenIntent);
//
//                }else{
//                    toastMessage("no ID associated with that Date");
//                }
//

   //     });
   // }


    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
