package com.example.yahya.finalproject;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jason on 04/01/2018.
 */

public class AutoViewListContents extends Activity{

    AutoDatabaseHelper myDB;
    ArrayList<Automobile> carList;
    ListView listView;
    Automobile car;

    TextView tvPrice, tvGasFilled, tvDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_listview);



        myDB = new AutoDatabaseHelper(this);

        carList = new ArrayList<>();
        Cursor data = myDB.getGasData();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(AutoViewListContents.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                car = new Automobile(
                        data.getString(1),
                        data.getString(2),
                        data.getString(3));
                carList.add(i,car);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                System.out.println(carList.get(i).getPrice());
                i++;
            }
            AutoListAdapter adapter =  new AutoListAdapter(this,R.layout.auto_list_adapter_view, carList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }

        // French localization
        tvPrice = findViewById(R.id.price_list_label);
        tvGasFilled = findViewById(R.id.gas_filled_label);
        tvDistance = findViewById(R.id.distance_label);
        tvPrice.setText(R.string.price_list_label);
        tvGasFilled.setText(R.string.gas_filled_label);
        tvDistance.setText(R.string.distance_label);
    }
}
