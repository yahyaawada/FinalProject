package com.example.yahya.finalproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason on 04/01/2018.
 */

public class AutoListAdapter extends ArrayAdapter<Automobile> {

    private LayoutInflater mInflater;
    private ArrayList<Automobile> cars;
    private int mViewResourceId;

    public AutoListAdapter(Context context, int textViewResourceId, ArrayList<Automobile> cars) {
        super(context, textViewResourceId, cars);
        this.cars = cars;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Automobile car = cars.get(position);

        // When working with values, convert to string and check if length > 0
        if (car != null) {
            TextView price = convertView.findViewById(R.id.textPrice);
            TextView litres = convertView.findViewById(R.id.textLitres);
            TextView kilometers = convertView.findViewById(R.id.textKilometers);
            if (price != null) {
                price.setText(car.getPrice());
            }
            if (litres != null) {
                litres.setText((car.getLitres()));
            }
            if (kilometers != null) {
                kilometers.setText((car.getKilometers()));
            }
        }

        return convertView;
    }
}