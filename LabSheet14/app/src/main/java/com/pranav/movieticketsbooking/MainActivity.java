package com.pranav.movieticketsbooking;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Use constants for keys to avoid typos
    private static final String PREFS_NAME = "MovieBookingPrefs";
    private static final String KEY_MOVIE_NAME = "MovieName";
    private static final String KEY_TICKETS_COUNT = "TicketsCount";

    private AutoCompleteTextView movieAutoComplete;
    private TextInputEditText ticketsEditText;
    private final String[] movies = {"Jawan", "Leo", "Tiger 3", "Ghost", "Salaar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAutoComplete = findViewById(R.id.movieAutoComplete);
        ticketsEditText = findViewById(R.id.ticketsEditText);
        Button submitBtn = findViewById(R.id.submitBtn);

        // Setup the adapter for the movie dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, movies);
        movieAutoComplete.setAdapter(adapter);
        submitBtn.setOnClickListener(v -> saveData());
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Load data every time the app is resumed
        loadData();
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String movieName = movieAutoComplete.getText().toString();
        String ticketsStr = Objects.requireNonNull(ticketsEditText.getText()).toString();
        // Validate that a movie is selected and tickets are entered
        if (movieName.isEmpty() || ticketsStr.isEmpty()) {
            Toast.makeText(this, "Please select a movie and enter ticket count", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int ticketsCount = Integer.parseInt(ticketsStr);
            editor.putString(KEY_MOVIE_NAME, movieName);
            editor.putInt(KEY_TICKETS_COUNT, ticketsCount);
            editor.apply();
            Toast.makeText(this, "Booking saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number of tickets", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Retrieve saved data, providing default values
        String savedMovie = sharedPreferences.getString(KEY_MOVIE_NAME, "");
        int savedTickets = sharedPreferences.getInt(KEY_TICKETS_COUNT, 0);

        // Update the UI
        movieAutoComplete.setText(savedMovie, false); // false prevents the dropdown from showing
        if (savedTickets > 0) {
            ticketsEditText.setText(String.valueOf(savedTickets));
        } else {
            ticketsEditText.setText(""); // Clear if default value is loaded
        }
    }
}