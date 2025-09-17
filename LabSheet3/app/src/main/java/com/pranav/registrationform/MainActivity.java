package com.pranav.registrationform;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String selectedGender = ""; // store gender text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.name_input);
        EditText fatherName = findViewById(R.id.father_name_input);
        RadioGroup genderGroup = findViewById(R.id.rb_group);
        AutoCompleteTextView place = findViewById(R.id.place_input);

        // Setup autocomplete
        String[] locations = {"Bangalore","Delhi","Pune","Bombay","Chennai","Hyderabad","Calcutta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, locations);
        place.setThreshold(1);
        place.setAdapter(adapter);

        // Track selected gender
        genderGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            selectedGender = rb.getText().toString();
        });

        findViewById(R.id.submit_button).setOnClickListener(v -> {
            String res = String.format(
                    "Name: %s\nFather's name: %s\nGender: %s\nPlace of Birth: %s",
                    name.getText().toString(),
                    fatherName.getText().toString(),
                    selectedGender,
                    place.getText().toString()
            );
            Toast.makeText(this, res , Toast.LENGTH_LONG).show();
        });
    }
}
