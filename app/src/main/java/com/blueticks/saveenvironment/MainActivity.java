package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

     private Button button;
     private TextView textview4;
     private TextView textview5;
     private TextView textview6;
     private EditText edittext1;
     private EditText edittext2;
     private EditText number1;
     private EditText number2;
     private EditText number3;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button2);
        textview4=findViewById(R.id.textView7);
        textview5=findViewById(R.id.textView9);
        textview6=findViewById(R.id.textView11);
        edittext1=findViewById(R.id.editText10);
        edittext1=findViewById(R.id.editText11);
        number1=findViewById(R.id.editTextNumberDecimal);
        number2=findViewById(R.id.editTextNumberDecimal2);
        number3=findViewById(R.id.editTextNumber);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}