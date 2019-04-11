package com.example.kynguyenba.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterList extends AppCompatActivity {

    ListView listViewRegister;
    static ArrayList<Candidate> dataObj = new ArrayList<Candidate>();
    ArrayAdapter<Candidate> objAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);

        listViewRegister = (ListView)findViewById(R.id.listviewRegister);

        Intent intent = getIntent();
        Object candidateObj = intent.getSerializableExtra("Candidate");
        Candidate candidate = (Candidate)candidateObj;
        dataObj.add(candidate);

        objAdapter = new ArrayAdapter<Candidate>(this, android.R.layout.simple_list_item_1, dataObj);
        listViewRegister.setAdapter(objAdapter);
    }
}
