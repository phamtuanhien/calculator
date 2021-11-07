package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //test
    private TextView minor;
    private TextView major;
    private StringBuilder stringBuilder;
    private String operator;
    private boolean needClearMajor;
    private boolean canClearResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minor = findViewById(R.id.input);
        major = findViewById(R.id.output);
        stringBuilder = new StringBuilder();
        operator = "";
        needClearMajor = false;
        canClearResult = false;
    }

    public void onClickDel(View view) {
        if (stringBuilder.length() > 0 ) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            major.setText(stringBuilder.toString());
        }
        if (stringBuilder.length() == 0) {
            stringBuilder = new StringBuilder("0");
            major.setText(stringBuilder.toString());
        }
    }

    public void onClickC(View view) {
        stringBuilder = new StringBuilder();
        operator = "";
        needClearMajor = false;
        canClearResult = false;
        minor.setText("");
        major.setText("0");
    }

    public void onClickNum(View view) {
        if (!minor.getText().toString().isEmpty() && needClearMajor == true) {
            needClearMajor = false;
            stringBuilder = new StringBuilder();
        }
        if (minor.getText().toString().isEmpty() && canClearResult == true) {
            canClearResult = false;
            stringBuilder = new StringBuilder();
        }
        stringBuilder.append(((Button)view).getText());
        major.setText(stringBuilder.toString());
    }

    public void onClickOperator(View view) {
        if (minor.getText().toString().isEmpty()) {
            stringBuilder.append(((Button)view).getText());
            minor.setText(stringBuilder.toString());
            operator = ((Button)view).getText().toString();
            needClearMajor = true;
        } else {
            if (needClearMajor) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(((Button)view).getText());
                minor.setText(stringBuilder.toString());
                operator = ((Button)view).getText().toString();
            } else {
                onClickEqual(findViewById(R.id.equal));
                stringBuilder.append(((Button)view).getText());
                minor.setText(stringBuilder.toString());
                operator = ((Button)view).getText().toString();
                needClearMajor = true;
            }
        }
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }
    public void onClickEqual(View view) {
        System.out.println(operator);
        int result = 0;
        if (operator.equals("+")) {
            result = Integer.parseInt(removeLastCharacter(minor.getText().toString())) + Integer.parseInt(major.getText().toString());
        }
        if (operator.equals("-")) {
            result = Integer.parseInt(removeLastCharacter(minor.getText().toString())) - Integer.parseInt(major.getText().toString());
        }
        if (operator.equals("×")) {
            result = Integer.parseInt(removeLastCharacter(minor.getText().toString())) * Integer.parseInt(major.getText().toString());
        }
        if (operator.equals("÷")) {
            result = Integer.parseInt(removeLastCharacter(minor.getText().toString())) + Integer.parseInt(major.getText().toString());
        }
        System.out.println(result);
        minor.setText("");
        major.setText(Integer.toString(result));
        stringBuilder = new StringBuilder(major.getText().toString());
        canClearResult = true;
    }
}