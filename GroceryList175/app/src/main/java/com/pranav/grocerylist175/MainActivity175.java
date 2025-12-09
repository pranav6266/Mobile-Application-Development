package com.pranav.grocerylist175;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity175 extends AppCompatActivity {
    EditText item ,quantity;
    Button insert , display;
    ListView listview175;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Initialize
        item = findViewById(R.id.item175);
        quantity = findViewById(R.id.quantity175);
        insert = findViewById(R.id.insert175);
        display = findViewById(R.id.display175);
        listview175 = findViewById(R.id.listview175);
        db = new DbHandler(this);

        insert.setOnClickListener(v -> {
            String newItem = item.getText().toString();
            int newQuantity = Integer.parseInt(quantity.getText().toString());
            boolean res = db.insertData(newItem , newQuantity);
            if(res) Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
        });


        display.setOnClickListener(v -> {
            Cursor cv = db.getData();
            ArrayList<String> arrayList = new ArrayList<>();
            while(cv.moveToNext()){
                arrayList.add(cv.getString(0)+ " | " + cv.getString(1));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
            listview175.setAdapter(adapter);
        });



    }
}