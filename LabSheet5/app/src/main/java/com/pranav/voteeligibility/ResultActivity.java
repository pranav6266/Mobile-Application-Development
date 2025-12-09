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
            res.setText(String.format("Name: %s\n" +
                    "Aadhar Number: %s\n" +
                    "You are eligible to vote because your age is %d",
                    r_name, r_aadhar, age));
        else
            res.setText(String.format("Name: %s\n" +
                            "Aadhar Number: %s\n" +
                            "You are not eligible to vote because your age is %d",
                    r_name, r_aadhar, age));
    }
}
