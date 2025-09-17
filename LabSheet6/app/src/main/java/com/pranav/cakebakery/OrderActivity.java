package com.pranav.cakebakery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        TextView result = findViewById(R.id.result);
            Intent in = getIntent();
            String cakeFlavour = in.getStringExtra("ITEM");
            String date = in.getStringExtra("DATE");
            String time = in.getStringExtra("TIME");
            result.setText("Your Order Placed Successfully!!"+"\nYour "+cakeFlavour+" cake will be delivered on "+date+"\nat "+time);
    }
}
