package com.pranav.bgmusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton playButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        stopButton = findViewById(R.id.stopButton);

        // Listener for the play button
        playButton.setOnClickListener(v -> {
            // Create an intent to start the service
            Intent startIntent = new Intent(this, MusicPlayerService.class);
            startService(startIntent);
        });

        // Listener for the stop button
        stopButton.setOnClickListener(v -> {
            // Create an intent to stop the service
            Intent stopIntent = new Intent(MainActivity.this, MusicPlayerService.class);
            stopService(stopIntent);
        });
    }
}

