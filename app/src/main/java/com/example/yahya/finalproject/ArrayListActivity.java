package com.example.yahya.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ArrayListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        ListView theList = (ListView)findViewById(R.id.listView);

        /* for simple ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.cell_layout,sourceData);

        */
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ListItemClick", "You clicked an item");
            }
        });
    }
}
