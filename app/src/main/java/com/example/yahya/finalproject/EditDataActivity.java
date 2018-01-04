package com.example.yahya.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends Activity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete, btnList, btnEntryScreen;
    private EditText editable_item;

    DatabaseHelperPhil mDatabaseHelper;

    private String selectedDate;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
      //  btnList = (Button) findViewById(R.id.btnlistViewBack);
        btnEntryScreen = (Button) findViewById(R.id.btnEntryScreen);
        editable_item = (EditText) findViewById(R.id.editable);

        mDatabaseHelper = new DatabaseHelperPhil(this);

        //
        Intent receivedIntent = getIntent();

        //
        selectedID = receivedIntent.getIntExtra("id", -1);

        //
        selectedDate = receivedIntent.getStringExtra("date");

        //editable_item.setText(selectedDate);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateDate(item, selectedID,selectedDate);

                }else{
                    toastMessage("You must enter a date!");
                }
            }
        });

            btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mDatabaseHelper.deleteDate(selectedID,selectedDate);

                        String value = editable_item.getText().toString();
                selectedID=Integer.parseInt(value);
                mDatabaseHelper.delete_byID(selectedID);
                editable_item.setText("");
                toastMessage("Item was removed from database");
            }
        });

//        btnList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editScreenIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
//                startActivity(editScreenIntent);
//            }
//        });

        btnEntryScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editScreenIntent = new Intent(EditDataActivity.this, ProjectToolbar.class);
                startActivity(editScreenIntent);
            }
        });

    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
