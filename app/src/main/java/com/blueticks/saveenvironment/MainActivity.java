package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

     private Button button;
     private TextView textview7;
     private TextView textview9;
     private TextView textview11;

    private TextView textview5;
    private TextView textview8;
    private TextView textview10;


     private EditText edittext1;
     private EditText edittext2;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button2);
        textview7=findViewById(R.id.textView7);
        textview9=findViewById(R.id.textView9);
        textview11=findViewById(R.id.textView11);

        textview5=findViewById(R.id.textView5);
        textview8=findViewById(R.id.textView8);
        textview10=findViewById(R.id.textView10);


        edittext1=findViewById(R.id.editText10);
        edittext1=findViewById(R.id.editText11);


        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}