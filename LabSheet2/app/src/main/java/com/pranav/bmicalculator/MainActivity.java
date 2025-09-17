package com.pranav.bmicalculator;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        EditText weight = findViewById(R.id.weight_input);
        EditText height = findViewById(R.id.height_input);
        TextView result = findViewById(R.id.result_text);

        findViewById(R.id.calculate_button).setOnClickListener(v ->
                calculateBmi(
                Double.parseDouble(weight.getText().toString()),
                Double.parseDouble(height.getText().toString()),
                result)
        );
    }

    public static void calculateBmi(double weight, double height, TextView result){
        height = height / 100;
        double bmi = weight / (height * height);
        if(bmi < 18.5) result.setText("BMI is : "+String.format("%.2f", bmi) + "\n Underweight");
        if (bmi >= 18.5 && bmi < 25) result.setText("BMI is : "+String.format("%.2f", bmi) + "\n Healthy");
        if (bmi >= 25) result.setText("BMI is : "+String.format("%.2f", bmi) + "\n Overweight");
    }
}