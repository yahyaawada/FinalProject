package com.example.yahya.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 04/01/18.
 */

public class ArrayListAdapter extends ArrayAdapter<Save>{

    private LayoutInflater mInflater;
    private ArrayList<Save> savers;
    private int mViewResourceId;

    public ArrayListAdapter(Context context, int textViewResourceId, ArrayList<Save> savers) {
        super(context, textViewResourceId, savers);
        this.savers = savers;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Save save = savers.get(position);

        if (save != null) {
            TextView date = (TextView) convertView.findViewById(R.id.textFirstName);
            TextView time = (TextView) convertView.findViewById(R.id.textLastName);
            TextView temperature = (TextView) convertView.findViewById(R.id.textFavFood);
            if (date != null) {
                date.setText(save.getDate());
            }
            if (time != null) {
                time.setText(save.getTime());
            }
            if (temperature!= null) {
                temperature.setText((save.getTemperature()));
            }
        }

        return convertView;
    }
}
