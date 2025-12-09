package com.pranav.healthmonitor;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText bpEditText, tempEditText;
    private static final String CHANNEL_ID = "health_vitals_channel";
    private static final int NOTIFICATION_ID = 100;

    // 1. Declare the launcher for the permission request
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Permission granted. You can now receive notifications.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied. Notifications will be blocked.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        askNotificationPermission(); // Ask for permission on startup

        bpEditText = findViewById(R.id.bpEditText);
        tempEditText = findViewById(R.id.tempEditText);
        Button submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(view -> processVitals());
    }

    private void processVitals() {
        String bpStr = Objects.requireNonNull(bpEditText.getText()).toString();
        String tempStr = Objects.requireNonNull(tempEditText.getText()).toString();

        if (TextUtils.isEmpty(bpStr) || TextUtils.isEmpty(tempStr)) {
            Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int bp = Integer.parseInt(bpStr);
            float temp = Float.parseFloat(tempStr);

            String message;
            if (bp >= 90 && bp <= 120 && temp >= 97.0 && temp <= 99.0) {
                message = "Your Vitals are fine.";
            } else {
                message = "You need to consult a doctor.";
            }

            String notificationText = "BP: " + bp + ", Temperature: " + temp + "°F";
            showNotification(message, notificationText);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    private void askNotificationPermission() {
        // 2. This is only necessary for API 33+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                // Launch the permission request
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    // The rest of your code (createNotificationChannel, showNotification) remains the same.
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Health Vitals";
            String description = "Notifications for user health vitals";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String message, String notificationText) {
        Intent intent = new Intent(this, NotificationResult.class);
        intent.putExtra("Message", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Ensure this icon exists
                .setContentTitle("Vital Conditions Update")
                .setContentText(notificationText)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}