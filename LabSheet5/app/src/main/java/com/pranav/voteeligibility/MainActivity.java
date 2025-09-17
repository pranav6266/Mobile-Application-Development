package com.pranav.voteeligibility;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int diff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userName = findViewById(R.id.userNameInput);
        EditText aadhaarNumber = findViewById(R.id.aadhaarInput);
        EditText date = findViewById(R.id.dateInput);
            date.setOnClickListener(v -> {
                Calendar c = Calendar.getInstance();
                int currentYear = c.get(Calendar.YEAR);
                int currentMonth = c.get(Calendar.MONTH);
                int currentDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(this,
                        (datePicker, year, month, dayOfMonth) -> {
                            date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            // Calculate age accurately
                            int age = currentYear - year;
                            if (currentMonth < month || (currentMonth == month && currentDay < dayOfMonth)) {
                                age--;
                            }
                            diff = age;
                        }, currentYear, currentMonth, currentDay);
                dialog.show();
            });

        findViewById(R.id.checkButton).setOnClickListener(v -> {
                String s_name = userName.getText().toString();
                String s_num = aadhaarNumber.getText().toString();
            Intent in = new Intent(getApplicationContext(), ResultActivity.class);
            in.putExtra("NAME",s_name);
            in.putExtra("AADHAR",s_num);
            in.putExtra("AGE", diff);
            startActivity(in);
        });
    }
}
