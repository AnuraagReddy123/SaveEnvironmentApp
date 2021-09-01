package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class TransportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView textview7;
    private TextView textview9;
    private TextView textview11;

    private TextView textview5;
    private TextView textview8;
    private TextView textview10;


    private EditText sourceEt;
    private EditText destEt;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        button=findViewById(R.id.button2);
        textview7=findViewById(R.id.textView7);
        textview9=findViewById(R.id.textView9);

        textview8=findViewById(R.id.distance_text);
        textview10=findViewById(R.id.amount_saved_text);


        sourceEt=findViewById(R.id.source_et);
        destEt=findViewById(R.id.dest_et);


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