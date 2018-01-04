package com.example.yahya.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yahya.finalproject.Tracking_Activity.ActivityTracking;


public class ProjectToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.project_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Final Project");
    }


    @Override
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {

        int id = mi.getItemId();

        switch (id) {

            // selecting the first icon
            case R.id.action_one:
                startActivity(new Intent(ProjectToolbar.this, ActivityTracking.class));
                break;
            // selecting the second icon
            case R.id.action_two:
                startActivity(new Intent(ProjectToolbar.this, NutritionActivity.class));
                break;
            // selecting the third icon
            case R.id.action_three:
                startActivity(new Intent(ProjectToolbar.this, ThermostatActivity.class));
                break;
            // selecting the fourth icon
            case R.id.action_four:
                startActivity(new Intent(ProjectToolbar.this, AutomobileActivity.class));
                break;
            // selecting the about icon
            case R.id.about:
                Toast.makeText(ProjectToolbar.this,"Version 1.0, Final Project", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }
}
