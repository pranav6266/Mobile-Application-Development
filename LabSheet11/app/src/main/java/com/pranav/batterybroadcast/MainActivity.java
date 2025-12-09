package com.pranav.batterybroadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private BatteryReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar batteryProgressBar = findViewById(R.id.batteryProgressBar);
        TextView batteryLevelTextView = findViewById(R.id.batteryLevelTextView);

        // Initialize the receiver
        batteryReceiver = new BatteryReceiver(batteryProgressBar, batteryLevelTextView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Register the receiver to listen for battery changes
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unregister the receiver to save resources when the app is not visible
        unregisterReceiver(batteryReceiver);
    }
}

