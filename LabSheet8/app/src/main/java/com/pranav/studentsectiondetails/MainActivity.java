package com.pranav.studentsectiondetails;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView studentListView;
    String[] studentNames = {"Meena", "Ravi", "Priya", "Amit", "Sunita", "Vikram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentListView = findViewById(R.id.studentListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentNames);
        studentListView.setAdapter(adapter);

        studentListView.setOnItemClickListener((parent, view, position, id) -> {
            StudentDetailsFragment detailsFragment = (StudentDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragmentContainer);
            if (detailsFragment != null) {
                detailsFragment.displayDetails(position);
            }
        });
    }
}
