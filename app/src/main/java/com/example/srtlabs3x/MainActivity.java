package com.example.srtlabs3x;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText number;
    TextView xmin;
    TextView xmax;
    TextView errorLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.numberTypedIn);
        xmin = findViewById(R.id.xMin);
        xmax = findViewById(R.id.xMax);
        errorLabel = findViewById(R.id.textError);
    }

    @SuppressLint("SetTextI18n")
    public void executeLab(View v) {
        xmin.setTextColor(Color.GREEN);
        xmax.setTextColor(Color.GREEN);
        errorLabel.setTextColor(Color.RED);
        fermatFactors();
    }

    @SuppressLint("SetTextI18n")
    public void fermatFactors() {
        if (number.getText().toString().length() == 0 || number.getText().toString().trim().equals("")) {
            errorLabel.setText("There is no data! Enter the number!");
            return;
        }

        // ***********************************************
        // Invalid data checking added
        if (!isNumeric(number.getText().toString())) {
            xmin.setText("");
            xmax.setText("");
            errorLabel.setText("Incorrect input!");
            return;
        }
        // *******************************************

        long n = Long.parseLong(number.getText().toString());

        if (n <= 0) {
            xmin.setText("");
            xmax.setText("");
            errorLabel.setText("Number should be odd positive integer only!");
            return;
        }

        if ((n & 1) == 0) {
            xmin.setText("");
            xmax.setText("");
            errorLabel.setText("Number should not be even!");
            return;
        }

        long a = (int) Math.ceil(Math.sqrt(n));

        if (a * a == n) {
            errorLabel.setText("");
            xmin.setText(Long.toString(a));
            xmax.setText(Long.toString(a));
            return;
        }
        int b;
        while (true) {
            long b1 = (a * a) - n;
            b = (int) (Math.sqrt(b1));
            if (b * b == b1)
                break;
            else
                a += 1;
        }
        errorLabel.setText("");
        xmin.setText(Long.toString(a - b));
        xmax.setText(Long.toString(a + b));
    }

    // ************************************************************************
    // Invalid data checking added
    public static boolean isNumeric(String x) throws NumberFormatException {
        try {
            Long.parseLong(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // ************************************************************************

    public void cleanForms(View v) {
        number.setText("");
        xmin.setText("");
        xmax.setText("");
        errorLabel.setText("");
    }
}