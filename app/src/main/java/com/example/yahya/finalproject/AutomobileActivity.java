package com.example.yahya.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AutomobileActivity extends Activity {

   /* String [] sourceData = { "  ","Activity Tracking", "  ", "Food Nutrition information tracker",
            "House Thermostat", "Automobile"}; */

    AutoDatabaseHelper dbObject;
    ArrayList<String> gasEntries = new ArrayList();

    EditText    etPrice, etLitres, etKilometers , etID;
    TextView    tvPrice, tvLitres, tvKilometers, tvEntry;
    Button      btnAddGasData, btnDeleteGasData, btnViewData, btnUpdateData, btnViewList;

    public static final String LOG_TAG = "AutomobileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);
        dbObject = new AutoDatabaseHelper(this);
        Log.i(LOG_TAG, "onCreate: db created ");

        etPrice = findViewById(R.id.price_field);
        etLitres = findViewById(R.id.litres_field);
        etKilometers = findViewById(R.id.km_field);
        etID = findViewById(R.id.id_field);

        btnAddGasData = findViewById(R.id.add_button);
        btnDeleteGasData = findViewById(R.id.delete_button);
        btnViewData = findViewById(R.id.view_button);
        btnUpdateData = findViewById(R.id.update_button);
        btnViewList = findViewById(R.id.view_list_button);

        // French localization
        tvPrice = findViewById(R.id.price_label);
        tvPrice.setText(R.string.price_label);
        tvLitres = findViewById(R.id.litres_label);
        tvLitres.setText(R.string.litres_label);
        tvKilometers = findViewById(R.id.kilometers_label);
        tvKilometers.setText(R.string.kilometers_label);
        tvEntry = findViewById(R.id.entry_label);
        tvEntry.setText(R.string.entry_label);
        btnAddGasData.setText(R.string.add_button);
        btnDeleteGasData.setText(R.string.delete_button);
        btnViewData.setText(R.string.view_button);
        btnUpdateData.setText(R.string.update_button);
        btnViewList.setText(R.string.view_list_button);
        // End of localization

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileActivity.this, AutoViewListContents.class);
                startActivity(intent);
            }
        });


        //Creating an array adapter for the listView
        //ListView lv = (ListView)findViewById(R.id.gas_list);
        //lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));
        addGasData();
        deleteGasData();
        viewData();
        updateGasData();
    }

    public void addGasData() {
        btnAddGasData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbObject.insertGasData(
                                etPrice.getText().toString(),
                                etLitres.getText().toString(),
                                etKilometers.getText().toString()
                        );
                        if(isInserted == true)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data was added successfully.",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "Data could not be added.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    } /* End of Method */

    public void deleteGasData() {
        btnDeleteGasData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer rowToDelete = dbObject.deleteGasData(etID.getText().toString());
                        if(rowToDelete > 0)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data deleted successfully.",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "There is no data to delete.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    } /* End of Method */

    public void updateGasData() {
        Log.i(LOG_TAG, "In updateGasData()");
        btnUpdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = dbObject.updateGasData(
                                etID.getText().toString(),
                                etPrice.getText().toString(),
                                etLitres.getText().toString(),
                                etKilometers.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data updated successfully.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "Data could not be updated.",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewData() {
        Log.i(LOG_TAG, "In viewData()");
        btnViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor c = dbObject.getGasData();
                        if(c.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (c.moveToNext()) {
                            buffer.append("Id: " + c.getString(0)+"\n");
                            buffer.append("Price: " + c.getString(1)+"\n");
                            buffer.append("Litres: " + c.getString(2)+"\n");
                            buffer.append("Kilometers: "+ c.getString(3)+"\n");
                            buffer.append("Date: " + c.getString(4)+"\n\n");
                        }
                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    } /* End of Method */

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    } /* End of Method */



    public class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return gasEntries.size();
        }

        public String getItem(int position) {
            return gasEntries.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = AutomobileActivity.this.getLayoutInflater();
            View result = null;

            result = inflater.inflate(R.layout.gas_entry_list, null);

            TextView text = result.findViewById(R.id.id_text);
            text.setText(getItem(position)); // get the string at position
            return result;
        }
    }

} /* End of Class AutomobileActivity */