package com.pranav.cakebakery;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] cakes = {"Vanilla", "Red Velvet", "Choclate", "Strawberry", "Butterscotch"};
    String selectedFlavour, selectedDate, selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner cakeName = findViewById(R.id.cakeNameSpinner);
        ImageView calender = findViewById(R.id.calenderImg);
        ImageView clock = findViewById(R.id.clockImg);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cakes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cakeName.setAdapter(adapter);
        cakeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFlavour = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        calender.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH);
            int currentDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this,
                    (datePicker, year, month, dayOfMonth) ->
                            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year, currentYear, currentMonth, currentDay);
                            dialog.show();
        });

        clock.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int currentHour = c.get(Calendar.HOUR_OF_DAY);
            int currentMinute = c.get(Calendar.MINUTE);

            TimePickerDialog dialog = new TimePickerDialog(this,
                    (timePicker, hourOfDay, minute) ->
                            selectedTime = hourOfDay + ":" + minute, currentHour, currentMinute, false);
                            dialog.show();
        });

        findViewById(R.id.orderButton).setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), OrderActivity.class);
            in.putExtra("ITEM",selectedFlavour);
            in.putExtra("DATE",selectedDate);
            in.putExtra("TIME",selectedTime);
            startActivity(in);
        });
    }
}