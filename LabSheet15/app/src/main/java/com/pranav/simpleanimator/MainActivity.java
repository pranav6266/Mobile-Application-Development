package com.pranav.simpleanimator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

// Implement OnClickListener to handle all button clicks in one method
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoImage = findViewById(R.id.logoImage);

        // Find all buttons and set the same listener for each
        findViewById(R.id.blinkBtn).setOnClickListener(this);
        findViewById(R.id.rotBtn).setOnClickListener(this);
        findViewById(R.id.fadeBtn).setOnClickListener(this);
        findViewById(R.id.moveBtn).setOnClickListener(this);
        findViewById(R.id.slideBtn).setOnClickListener(this);
        findViewById(R.id.zoomBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int animationResource = 0;

        // Use a switch statement to find the correct animation for the clicked button
        final int viewId = view.getId();
        if (viewId == R.id.blinkBtn) {
            animationResource = R.anim.blink;
        } else if (viewId == R.id.rotBtn) {
            animationResource = R.anim.rotate;
        } else if (viewId == R.id.fadeBtn) {
            animationResource = R.anim.fade;
        } else if (viewId == R.id.moveBtn) {
            animationResource = R.anim.move;
        } else if (viewId == R.id.slideBtn) {
            animationResource = R.anim.slide;
        } else if (viewId == R.id.zoomBtn) {
            animationResource = R.anim.zoom;
        }

        // Load and start the selected animation
        if (animationResource != 0) {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animationResource);
            logoImage.startAnimation(animation);
        }
    }
}