package com.example.kynguyenba.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class CalculatorHistory extends AppCompatActivity {
    EditText numberAHistory, numberBHistory;
    private static TextView txtResultHistory;
    private static ListView listViewHistory;
    private static Button btnSend;
    private int position;
    private static ArrayAdapter<String> adapter = null;
    ArrayList<String> datalist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_history);
        btnSend = (Button)findViewById(R.id.btnSend);
        numberAHistory = (EditText)findViewById(R.id.numberAhistory);
        numberBHistory = (EditText)findViewById(R.id.numberBhistory);
        listViewHistory = (ListView)findViewById(R.id.listviewhistory);

        Intent intent = getIntent();
        Bundle bundle_ex = intent.getExtras();

        if(bundle_ex != null)
        {
            datalist = (ArrayList<String>) bundle_ex.get("data");
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datalist);
        listViewHistory.setAdapter(adapter);
        registerForContextMenu(listViewHistory);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberA_ex = numberAHistory.getText().toString();
                String numberB_ex = numberBHistory.getText().toString();
                Intent intent = new Intent(CalculatorHistory.this, CalculatorActivity.class);
                intent.putExtra("numberA", numberA_ex);
                intent.putExtra("numberB", numberB_ex);
                startActivity(intent);
            }
        });

        listViewHistory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listviewhistory) {
            ListView lv = (ListView) v;
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
            menu.setHeaderTitle(""+listViewHistory.getItemAtPosition(position));
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
}
