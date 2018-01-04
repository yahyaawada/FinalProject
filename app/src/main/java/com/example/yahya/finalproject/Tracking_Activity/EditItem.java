package com.example.yahya.finalproject.Tracking_Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yahya.finalproject.R;

public class EditItem extends Activity {

    TextView id, date, activity, duration, comment;
    DATABASEAdapter myDB;
    Cursor cursor;
    Button update;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        final String ACTIVITY_NAME = "EditActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String getID = bundle.getString("KEY_ID");

        id = (TextView)findViewById(R.id.edit_id);
        date = (TextView)findViewById(R.id.edit_date);
        activity = (TextView)findViewById(R.id.edit_activity);
        duration = (TextView)findViewById(R.id.edit_duration);
        comment = (TextView)findViewById(R.id.edit_comment);

        update = (Button)findViewById(R.id.button_edit);
        delete = (Button)findViewById(R.id.delete_data);

        myDB = new DATABASEAdapter(this);
        myDB.open();

        cursor = myDB.getRow(getID);
        cursor.moveToFirst();

        id.setText(cursor.getString(cursor.getColumnIndex(DATABASEAdapter.KEY_ID)));
        date.setText(cursor.getString(cursor.getColumnIndex(DATABASEAdapter.CREATED_AT)));
        activity.setText(cursor.getString(cursor.getColumnIndex(DATABASEAdapter.KEY_ACTIVITY)));
        duration.setText(cursor.getString(cursor.getColumnIndex(DATABASEAdapter.KEY_TIME)));
        comment.setText(cursor.getString(cursor.getColumnIndex(DATABASEAdapter.KEY_COMMENT)));




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String activity2 = activity.getText().toString();
                final String duration2 = duration.getText().toString();
                final String comment2 = comment.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.tracking_custom_dialog, null))
                        // Add action buttons
                        .setPositiveButton("YES ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                myDB.updateRow(getID, activity2, duration2, comment2);
                                Log.i("Database", "updated row "+getID);
                                Intent startIntent = new Intent(EditItem.this, Database_List.class);
                                startActivity(startIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.tracking_custom_dialog, null))
                        // Add action buttons
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                myDB.deleteRow(getID);
                                Log.i("Database", "deleted row "+getID);
                                Intent startIntent = new Intent(EditItem.this, Database_List.class);
                                startActivity(startIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        final String ACTIVITY_NAME = "EditItemActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        final String ACTIVITY_NAME = "EditItemActivity";
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        final String ACTIVITY_NAME = "EditItemActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        final String ACTIVITY_NAME = "EditItemActivity";
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        final String ACTIVITY_NAME = "EditItemActivity";
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
        final String ACTIVITY_NAME = "EditItemActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
