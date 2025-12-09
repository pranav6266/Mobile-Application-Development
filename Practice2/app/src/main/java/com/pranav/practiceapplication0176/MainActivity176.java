package com.pranav.practiceapplication0176;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity176 extends AppCompatActivity {

    EditText name_176, roll_176;
    Spinner dept_176;
    Button display, insert;
    ListView result;
    DbHandler176 dbHandler176;
    String selected_dept;
    String[] depts = {"CSE", "ISE", "ECE", "EEE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initialize Views
        name_176 = findViewById(R.id.name_176);
        roll_176 = findViewById(R.id.roll_176);
        dept_176 = findViewById(R.id.dept_176);
        display = findViewById(R.id.display);
        insert = findViewById(R.id.insert);
        result = findViewById(R.id.results);
        dbHandler176 = new DbHandler176(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, depts);
        dept_176.setAdapter(spinnerAdapter);

        dept_176.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_dept = dept_176.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        Insertion
        insert.setOnClickListener(v -> {
            String name = name_176.getText().toString();
            String roll_no = roll_176.getText().toString();


            boolean res = dbHandler176.insertData(name, roll_no, selected_dept);
            if(res) Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
        }
        );

//        View the data
        display.setOnClickListener(v ->{
            Cursor res = dbHandler176.getAllData_176();
            if(res.getCount() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<String> list = new ArrayList<>();
            while(res.moveToNext()){
                list.add(res.getString(0) + " | " + res.getString(1) + " | " +  res.getString(2));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            result.setAdapter(adapter);
        });
    }
}