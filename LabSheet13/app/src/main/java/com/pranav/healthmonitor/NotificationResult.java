package com.pranav.healthmonitor;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationResult extends AppCompatActivity {

    private TextView resultTextView;
    // Use the same notification ID from MainActivity to cancel it
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_result);
        resultTextView = findViewById(R.id.resultTextView);

        // Process the intent that started this activity
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // Process new intents if the activity is already running
        setIntent(intent); // Update the activity's intent
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        // Dismiss the notification that brought the user here
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);

        // Get the message from the intent's extras and display it
        Bundle extras = intent.getExtras();
        if (extras != null && extras.containsKey("Message")) {
            String msg = extras.getString("Message");
            resultTextView.setText("Report: " + msg);
        }
    }
}