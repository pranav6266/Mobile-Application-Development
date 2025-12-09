package com.pranav.practice_175;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity_123 extends AppCompatActivity {

    EditText etUser_123;
    Button btnLogin_123, btnView_123;
    ListView listView_123;
    DbHandler_123 DB_123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize Views (Append _123 to everything!)
        etUser_123 = findViewById(R.id.etUsername_123);
        btnLogin_123 = findViewById(R.id.btnLogin_123);
        btnView_123 = findViewById(R.id.btnView_123);
        listView_123 = findViewById(R.id.listView_123);
        DB_123 = new DbHandler_123(this);

        // 2. Ask for Location Permission immediately
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        // 3. LOGIN BUTTON LOGIC
        btnLogin_123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etUser_123.getText().toString();

                // GET TIMESTAMP
                String currentTime = new Date().toString();

                // GET LOCATION
                String locationStr = "Unknown Location";
                LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

                // Permission Check (Required by Android Studio)
                if (ActivityCompat.checkSelfPermission(MainActivity_123.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Get last known location (Simple way)
                    Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    // Fallback to Network provider if GPS is null
                    if(loc == null) loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if(loc != null) {
                        locationStr = "Lat: " + loc.getLatitude() + ", Lon: " + loc.getLongitude();
                    }
                }

                // SAVE TO DB
                Boolean check = DB_123.insertLogin_123(name, currentTime, locationStr);
                if(check) Toast.makeText(MainActivity_123.this, "Login Logged!", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. VIEW BUTTON LOGIC (Standard for all questions)
        btnView_123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB_123.getHistory_123();
                ArrayList<String> list = new ArrayList<>();
                while(res.moveToNext()){
                    list.add("User: " + res.getString(0) + "\nTime: " + res.getString(1) + "\nLoc: " + res.getString(2));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity_123.this, android.R.layout.simple_list_item_1, list);
                listView_123.setAdapter(adapter);
            }
        });


    }
}