package com.example.kynguyenba.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CalculatorActivity extends AppCompatActivity {

    TextView numberAActivity, numberBActivity;
    Button btnAdditionActivity, btnSubtractionActivity, btnMultiplicationActivity, btnDivisionActivity, btnSendResult;
    private static TextView txtResultActivity;
    private static String ADDITION = "+";
    private static String SUBTRACTION = "-";
    private static String MULTIPLICATION = "*";
    private static String DIVISION = "/";
    private String result="";
    private static  ArrayList<String> data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //find
        btnSendResult = (Button) findViewById(R.id.btnSendResult);
        numberAActivity = (TextView)findViewById(R.id.numberAActivity);
        numberBActivity = (TextView)findViewById(R.id.numberBActivity);
        btnAdditionActivity = (Button)findViewById(R.id.btnAdditionActivity);
        btnSubtractionActivity = (Button)findViewById(R.id.btnSubtractionActivity);
        btnMultiplicationActivity = (Button)findViewById(R.id.btnMultiplicationActivity);
        btnDivisionActivity = (Button)findViewById(R.id.btnDivisionActivity);
        txtResultActivity = (TextView)findViewById(R.id.txtResultActivity);
        //
        Intent intent = getIntent();
        Bundle bundle_ex = intent.getExtras();

        if(bundle_ex != null)
        {
            String numberA =(String) bundle_ex.get("numberA");
            numberAActivity.setText(numberA);
            String numberB =(String) bundle_ex.get("numberB");
            numberBActivity.setText(numberB);
        }

        btnAdditionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberAActivity.getText().toString().equals("") || numberBActivity.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResultActivity,result,ADDITION,Integer.parseInt(numberAActivity.getText().toString()),Integer.parseInt(numberBActivity.getText().toString()), data);
            }
            }
        });

        btnSubtractionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberAActivity.getText().toString().equals("") || numberBActivity.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResultActivity,result,SUBTRACTION,Integer.parseInt(numberAActivity.getText().toString()),Integer.parseInt(numberBActivity.getText().toString()), data);
            }
            }
        });

        btnDivisionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberAActivity.getText().toString().equals("") || numberBActivity.getText().toString().equals("") || numberBActivity.getText().toString().equals("0"))
            {
                showErrorToast();
            } else {
                showResult(txtResultActivity,result,DIVISION,Integer.parseInt(numberAActivity.getText().toString()),Integer.parseInt(numberBActivity.getText().toString()), data);
            }
            }
        });

        btnMultiplicationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberAActivity.getText().toString().equals("") || numberBActivity.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResultActivity,result,MULTIPLICATION,Integer.parseInt(numberAActivity.getText().toString()),Integer.parseInt(numberBActivity.getText().toString()), data);
            }
            }
        });

        btnSendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ResultCalculator = txtResultActivity.getText().toString();
                Intent intent = new Intent(CalculatorActivity.this, CalculatorHistory.class);
                intent.putStringArrayListExtra("data",data);
                startActivity(intent);
            }
        });
    }

    public void showResult(View view, String result, String calculation, int a, int b, ArrayList<String> data){
        float res = 0;
        if(calculation.equals(ADDITION)){
            res = Addition(a,b);
        } else if (calculation.equals(MULTIPLICATION)){
            res = Multiplication(a,b);
        }else if (calculation.equals(SUBTRACTION)){
            res = Subtraction(a,b);
        }else if (calculation.equals(DIVISION)) {
            res = Division(a, b);
        }
        result = a + " " + calculation + " " + b + " = " + res;
        data.add(result);
        ((TextView)view).setText(result);
    }

    public float Addition(int a, int b) {
        return (a) + (b);
    }

    public float Subtraction(int a, int b) {
        return a - b;
    }

    public float Multiplication(int a, int b) {
        return a * b;
    }

    public float Division(int a, int b) {
        return a / b;
    }

    public void showErrorToast() {
        Toast.makeText(this, "Input Number Error!", Toast.LENGTH_SHORT).show();
    }
}
