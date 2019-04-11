package com.example.kynguyenba.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    EditText numberA, numberB;
    Button btnAddition, btnSubtraction, btnMultiplication, btnDivision;
    private static TextView txtResult;
    private static ListView listView;
    ArrayAdapter<String> adapter = null;
    ArrayList<String> datalist = new ArrayList<String>();
    int a = 0;
    int b = 0;
    private int position;
    private String DELETE = "Delete";
    private static String TITLE = "";
    private static String ADD = "+";
    private static String ADDITION = "+";
    private static String SUBTRACTION = "-";
    private static String MULTIPLICATION = "*";
    private static String DIVISION = "/";
    private String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        Intent intent = new Intent(MainActivity.this, RegisterForm.class);
        startActivity(intent);

        numberA = (EditText)findViewById(R.id.numberA);
        numberB = (EditText)findViewById(R.id.numberB);
        btnAddition = (Button)findViewById(R.id.btnAddition);
        btnSubtraction = (Button)findViewById(R.id.btnSubtraction);
        btnMultiplication = (Button)findViewById(R.id.btnMultiplication);
        btnDivision = (Button)findViewById(R.id.btnDivision);
        txtResult = (TextView)findViewById(R.id.txtResult);
        listView = (ListView)findViewById(R.id.listview);
        registerForContextMenu(listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalist);
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberA.getText().toString().equals("") || numberB.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResult,result,datalist,adapter,ADDITION,Integer.parseInt(numberA.getText().toString()),Integer.parseInt(numberB.getText().toString()));
            }
            }
        });

        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberA.getText().toString().equals("") || numberB.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResult,result,datalist,adapter,SUBTRACTION,Integer.parseInt(numberA.getText().toString()),Integer.parseInt(numberB.getText().toString()));
            }
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberA.getText().toString().equals("") || numberB.getText().toString().equals("") || numberB.getText().toString().equals("0"))
            {
                showErrorToast();
            } else {
                showResult(txtResult,result,datalist,adapter,DIVISION,Integer.parseInt(numberA.getText().toString()),Integer.parseInt(numberB.getText().toString()));
            }
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(numberA.getText().toString().equals("") || numberB.getText().toString().equals(""))
            {
                showErrorToast();
            } else {
                showResult(txtResult,result,datalist,adapter,MULTIPLICATION,Integer.parseInt(numberA.getText().toString()),Integer.parseInt(numberB.getText().toString()));
            }
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listview) {
            ListView lv = (ListView) v;
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
            menu.setHeaderTitle(""+listView.getItemAtPosition(position));
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_delete) {
            datalist.remove(position);
            adapter.notifyDataSetInvalidated();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showResult(View view, String result, ArrayList<String> datalist, ArrayAdapter<String> adapter, String calculation, int a, int b){
        float res = 0;
        if(calculation.equals(ADDITION)){
            res = Addition(a,b);
        } else if (calculation.equals(MULTIPLICATION)){
            res = Multiplication(a,b);
        }else if (calculation.equals(SUBTRACTION)){
            res = Subtraction(a,b);
        }else if (calculation.equals(DIVISION)){
            res = Division(a,b);
        }
        result = a + " " + calculation + " " + b + " = " + res;
        ((TextView)view).setText(result);
        datalist.add(result);
        adapter.notifyDataSetInvalidated();
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
