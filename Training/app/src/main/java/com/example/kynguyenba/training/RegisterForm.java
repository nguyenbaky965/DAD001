package com.example.kynguyenba.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterForm extends AppCompatActivity {

    ArrayList<Objects> arrayListObjectData = new ArrayList<Objects>();
    EditText edtFullName, edtEmail, edtPhone;
    RadioButton radioMale, radioFeMale;
    RadioGroup radioGroup;
    Spinner spinPosition, spinLocation;
    CheckBox ckbEnglish, ckbJapanese, ckbFrench;
    Button btnApplyNow, btnViewList;
    Candidate candidate;
    private static String fullname = "Nguyen Ba Ky";
    private static String email = "ngbaky47@gmail.com";
    private static String phone = "0905893508";
    private static String gender = "Male";
    private static String postion = "Programmer";
    private static String location = "Da Nang";
    private String language;
    List<String> listPosition = new ArrayList<>();
    List<String> listLocation = new ArrayList<>();
    ArrayAdapter<String> adapterSpinPosition;
    ArrayAdapter<String> adapterSpinLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        edtFullName = (EditText)findViewById(R.id.edtFullName);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhone = (EditText)findViewById(R.id.edtPhone);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioMale = (RadioButton)findViewById(R.id.radioMale);
        radioFeMale = (RadioButton)findViewById(R.id.radioFeMale);

        spinPosition = (Spinner)findViewById(R.id.spinPosition);
        spinLocation = (Spinner)findViewById(R.id.spinLocation);

        ckbEnglish = (CheckBox)findViewById(R.id.ckbEnglish);
        ckbJapanese = (CheckBox)findViewById(R.id.ckbJapanese);
        ckbFrench = (CheckBox)findViewById(R.id.ckbFrench);

        btnApplyNow = (Button)findViewById(R.id.btnApplyNow);
        btnViewList = (Button)findViewById(R.id.btnViewList);

        final String fullname = edtFullName.getText().toString();
        final String email = edtEmail.getText().toString();
        final String phone = edtPhone.getText().toString();

        language = "";

        listPosition.add("Java");
        listPosition.add("Android");
        listPosition.add("PHP");
        listPosition.add("C#");
        listPosition.add("ASP.NET");

        adapterSpinPosition = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listPosition);
        adapterSpinPosition.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinPosition.setAdapter(adapterSpinPosition);

        spinPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegisterForm.this, spinPosition.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                postion = spinPosition.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listLocation.add("Da Nang");
        listLocation.add("Ha Noi");
        listLocation.add("TP Ho Chi Minh");
        adapterSpinLocation = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listLocation);
        adapterSpinLocation.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinLocation.setAdapter(adapterSpinLocation);

        spinLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegisterForm.this, spinLocation.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                location = spinLocation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnDifficultyLevelChanged(group, checkedId);
            }
        });

        radioMale.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView,isChecked);
            }
        });

        radioFeMale.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doOnGameCharacterChanged(buttonView,isChecked);
            }
        });

        btnApplyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                language = "";
                if(ckbEnglish.isChecked()){
                    language += "English";
                }
                if(ckbJapanese.isChecked()){
                    language += "Japanese";
                }
                if(ckbFrench.isChecked()){
                    language += "French";
                }
                if(language.equals(""))
                {
                    language = "English";
                }
                candidate = new Candidate(edtFullName.getText().toString(),edtEmail.getText().toString(),edtPhone.getText().toString(),gender,postion,location,language);
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterForm.this, RegisterList.class);
                intent.putExtra("Candidate", candidate);
                startActivity(intent);
            }
        });
    }

    private void doOnDifficultyLevelChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.radioMale) {
            gender = "Male";
        } else if(checkedRadioId== R.id.radioFeMale ) {
            gender = "FeMale";
        }
    }

    private void doOnGameCharacterChanged(CompoundButton buttonView, boolean isChecked)  {
        RadioButton radio =(RadioButton) buttonView;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fullname = edtFullName.getText().toString();
        email = edtEmail.getText().toString();
        phone = edtPhone.getText().toString();
    }
}
