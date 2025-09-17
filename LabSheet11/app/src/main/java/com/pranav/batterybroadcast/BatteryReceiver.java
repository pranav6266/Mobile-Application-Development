package com.pranav.batterybroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.BatteryManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver {

    private final ProgressBar progressBar;
    private final TextView textView;

    // Constructor to get references to the UI elements from MainActivity
    public BatteryReceiver(ProgressBar progressBar, TextView textView) {
        this.progressBar = progressBar;
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Extract the battery level from the Intent's "extra" data
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        // Update the UI
        if (level != -1) {
            textView.setText(level + "%");
            progressBar.setProgress(level);

            // Change progress bar color based on battery level
            if (level <= 20) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
            } else if (level <= 50) {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFC107"))); // Amber
            } else {
                progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50"))); // Green
            }
        }
    }
}
