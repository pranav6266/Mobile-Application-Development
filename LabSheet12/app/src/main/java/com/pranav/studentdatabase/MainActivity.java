package com.pranav.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText studentNameEditText, studentIdEditText;
    Button addButton, deleteButton, displayButton;
    TextView resultsTextView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        studentNameEditText = findViewById(R.id.studentNameEditText);
        studentIdEditText = findViewById(R.id.studentIdEditText);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        displayButton = findViewById(R.id.displayButton);
        resultsTextView = findViewById(R.id.resultsTextView);
        resultsTextView.setMovementMethod(new ScrollingMovementMethod()); // Make TextView scrollable

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);

        // Set button listeners
        addButton.setOnClickListener(v -> addStudent());
        deleteButton.setOnClickListener(v -> deleteStudent());
        displayButton.setOnClickListener(v -> displayStudents());
    }

    private void addStudent() {
        String name = studentNameEditText.getText().toString();
        String id = studentIdEditText.getText().toString();

        if (name.isEmpty() || id.isEmpty()) {
            Toast.makeText(this, "Please enter both name and ID", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isAdded = databaseHelper.addStudent(name, id);
        if (isAdded) {
            Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
            studentNameEditText.setText("");
            studentIdEditText.setText("");
            displayStudents(); // Refresh the display
        } else {
            Toast.makeText(this, "Error: Could not add student (ID might already exist)", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        String idStr = studentIdEditText.getText().toString();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter the ID to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        // CORRECTED: We removed the Integer.parseInt() call completely.
        boolean isDeleted = databaseHelper.deleteStudent(idStr);
        if (isDeleted) {
            Toast.makeText(this, "Student Deleted Successfully", Toast.LENGTH_SHORT).show();
            studentIdEditText.setText("");
            displayStudents(); // Refresh the display
        } else {
            Toast.makeText(this, "Error: No student found with that ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayStudents() {
        String allStudents = databaseHelper.getAllStudents();
        resultsTextView.setText(allStudents);
    }
}

