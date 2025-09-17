package com.pranav.labsheet1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText number1 = findViewById(R.id.first_input);
        EditText number2 = findViewById(R.id.second_input);
        TextView result = findViewById(R.id.res_view);

        findViewById(R.id.add_button).setOnClickListener(v -> calculate(number1, number2, result, "+"));
        findViewById(R.id.sub_button).setOnClickListener(v -> calculate(number1, number2, result, "-"));
        findViewById(R.id.multiply_button).setOnClickListener(v -> calculate(number1, number2, result, "*"));
        findViewById(R.id.divide_button).setOnClickListener(v -> calculate(number1, number2, result, "/"));
    }

    private void calculate(EditText n1, EditText n2, TextView result, String op) {
        try {
            double a = getNum(n1), b = getNum(n2), res = 0;
            switch (op) {
                case "+": res = a + b; break;
                case "-": res = a - b; break;
                case "*": res = a * b; break;
                case "/": res = b != 0 ? a / b : Double.NaN; break;
            }
            result.setText(String.format("%s of %s and %s is %s",
                    getOpName(op), formatNum(a), formatNum(b), formatNum(res)));
        } catch (NumberFormatException e) {
            result.setText("Invalid input!");
        }
    }

    private String formatNum(double num) {
        if (num == (long) num)  // check if it's a whole number
            return String.format("%d", (long) num);
        else
            return String.format("%.4f", num);
    }

    private double getNum(EditText number) {
        return Double.parseDouble(number.getText().toString());
    }

    private String getOpName(String op) {
        switch (op) {
            case "+": return "Addition";
            case "-": return "Subtraction";
            case "*": return "Multiplication";
            case "/": return "Division";
            default: return "Result";
        }
    }
}
