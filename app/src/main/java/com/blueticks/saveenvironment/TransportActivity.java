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

import java.util.HashMap;
import java.util.Map;

public class TransportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Button button;
    private TextView textview7;
    private TextView textview9;
    private TextView textview11;

    private TextView textview5;
    private TextView distance_text;
    private TextView amount_saved_text;


    private EditText sourceEt;
    private EditText destEt;

    private Spinner spinner;

    private String SpinnerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        button=findViewById(R.id.button2);
        textview7=findViewById(R.id.textView7);
        textview9=findViewById(R.id.textView9);

        distance_text = findViewById(R.id.distance_text);
        amount_saved_text = findViewById(R.id.amount_saved_text);


        sourceEt=findViewById(R.id.source_et);
        destEt=findViewById(R.id.dest_et);


        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        String src = sourceEt.getText().toString().trim();
        String dest = destEt.getText().toString().trim();

        double distance = 2.0;    // in km
        float petrol_price = 100.0f;
        float mileage_car = 20.0f;
        float mileage_bike = 60.0f;

        double cost_car = petrol_price * (distance/mileage_car);
        double cost_bike = petrol_price * (distance/mileage_bike);
        double cost_walking = 0;

        double saved_walking = cost_car - cost_walking;
        double saved_bike = cost_car - cost_bike;
        double saved_car = 0;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distance_text.setText(Double.toString(distance));
                if(SpinnerItem.equals("Car")){
                    amount_saved_text.setText(Double.toString(saved_car));  
                }
                else if(SpinnerItem.equals("Bike")){
                    amount_saved_text.setText(Double.toString(saved_bike));
                }
                else if(SpinnerItem.equals("Walk")){
                    amount_saved_text.setText(Double.toString(saved_walking));
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        SpinnerItem = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}