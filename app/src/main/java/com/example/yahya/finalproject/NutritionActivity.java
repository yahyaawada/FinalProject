package com.example.yahya.finalproject;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NutritionActivity extends Activity {

    DataHelp myDb;


    ArrayList<String> nutVals;
    ArrayList<String> finalVals = new ArrayList<>();

    String [] sourceData = { "  ","Activity Tracking", "  ", "Food Nutrition information tracker",
            "House Thermostat", "Automobile"};

    Button btnAdd;
    Button btnDel;
    EditText carbs;
    EditText cals;
    EditText fat;
    EditText rowToDelete;
    ListView entries;

    ChatAdapter messageAdapter;

    public static final String DATABASE_NAME = "nutrition.db";
    public static final String TABLE_NAME = "food_intake";
    public static final String COL_ID = "id";
    public static final String COL_CAL = "calories";
    public static final String COL_FAT = "fat";
    public static final String COL_CARBS = "Carbs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        carbs = findViewById(R.id.carbo);
        cals = findViewById(R.id.cals);
        fat = findViewById(R.id.fats);
        entries = findViewById(R.id.list_results);
        nutVals = new ArrayList<>();
        rowToDelete = findViewById(R.id.whatRow);
        //viewData();

        myDb = new DataHelp(this);

        messageAdapter = new ChatAdapter(this);
        entries.setAdapter(messageAdapter);





         Cursor res = myDb.getAllData();


        res.moveToFirst();
        int numOfRows = res.getCount();

        for(int i = 0 ;i<numOfRows;i++ ){

            nutVals.add("id : "+res.getString(0)+ "Calories :"
                    + res.getString(1)+"Carbs :" +res.getString(2)+" Fat : "+res.getString(3));

            res.moveToNext();
        }

        res.moveToFirst();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nutVals.add( cals.getText().toString() + "\n" + carbs.getText().toString()+
                        "\n"+fat.getText().toString()+"\n"+"Total Calories: ");

                messageAdapter.notifyDataSetChanged();

                boolean isInserted = myDb.insertData(cals.getText().toString(),carbs.getText().toString(),fat.getText().toString());

                if(isInserted = true){
                    Toast.makeText(NutritionActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{ Toast.makeText(NutritionActivity.this,"Not Complete", Toast.LENGTH_LONG).show();}






            }
        });







        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                myDb.deleteData(rowToDelete.getText().toString());
                showMessage("Want to delete a row", "You are about to delete something from the database");
                messageAdapter.notifyDataSetChanged();
            }
        });



    }//Oncreate



    public void viewData(){

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



    public  void showMessage(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();

    }




    private class DataHelp extends SQLiteOpenHelper {

        public DataHelp(Context c) {
            super(c, DATABASE_NAME, null, 1);


        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, calories TEXT , fat TEXT, carbs TEXT)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);

        }


        public boolean insertData(String cals, String carbs, String fats) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_CAL, cals);
            contentValues.put(COL_CARBS, carbs);
            contentValues.put(COL_FAT, fats);
            long result = db.insert(TABLE_NAME, null, contentValues);

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

        public Cursor getAllData() {

            SQLiteDatabase db = this.getWritableDatabase();


            Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
            return res;
        }

        public void dropRow(SQLiteDatabase sqLiteDatabase, int i) {

            sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + i);
        }


        public void deleteData(String row) {

            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + row + "'";
            db.execSQL(query);
            db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
            //db.execSQL("DELETE FROM "+TABLE_NAME);
            Toast.makeText(NutritionActivity.this,"QUERY EXECUTED",Toast.LENGTH_LONG).show();
        }




    }











    private class ChatAdapter extends ArrayAdapter<String>{


    public ChatAdapter(Context ctx){
        super(ctx,0);

    }

        public int getCount(){return nutVals.size();}

        public String getItem(int position){return nutVals.get(position);}

        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = NutritionActivity.this.getLayoutInflater();

          View result = null;

            if(position%2==0){
             result = inflater.inflate(R.layout.incoming_nutrition_values,null);}
            else{ result = inflater.inflate(R.layout.incoming_nutrition_values,null);}

            TextView message = result.findViewById(R.id.textView1);
            message.setText(getItem(position));
            return result;
        }


    }






}



