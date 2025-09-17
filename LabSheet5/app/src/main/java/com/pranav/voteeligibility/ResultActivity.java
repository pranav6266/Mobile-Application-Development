package com.pranav.voteeligibility;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView res = findViewById(R.id.result);
        Intent intent = getIntent();
        String r_name = intent.getStringExtra("NAME");
        String r_aadhar = intent.getStringExtra("AADHAR");
        int age = intent.getIntExtra("AGE",0);
        if(age>=18)
            res.setText("Name: "+r_name+"\nAadhar Number: "+r_aadhar+"\n You are eligible to vote");
        else
            res.setText("Name: "+r_name+"\nAadhar Number: "+r_aadhar+"\n You are not eligible to vote");

    }
}
