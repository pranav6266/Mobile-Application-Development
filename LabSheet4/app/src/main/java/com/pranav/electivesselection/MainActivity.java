package com.pranav.electivesselection;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name_input);
        EditText rollNo = findViewById(R.id.roll_input);
        TextView result = findViewById(R.id.result);
        Spinner electiveSpinner = findViewById(R.id.elective_spinner);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.courses)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        electiveSpinner.setAdapter(adapter);
        electiveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedCourse = adapterView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        findViewById(R.id.submit_button).setOnClickListener(v ->
                result.setText(String.format(
                        "Student's Name: %s\nRoll No. : %s\nDiscipline Elective : %s",
                        name.getText().toString(),
                        rollNo.getText().toString(),
                        selectedCourse))
        );

        findViewById(R.id.reset_button).setOnClickListener(v -> {
            name.setText("");
            rollNo.setText("");
            result.setText("");
        });
    }
}