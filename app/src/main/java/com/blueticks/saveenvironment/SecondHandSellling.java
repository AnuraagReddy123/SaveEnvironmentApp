package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondHandSellling extends AppCompatActivity {

    private EditText soldAmount;
    private EditText profitEarned;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_hand_sellling);

        soldAmount = findViewById(R.id.soldAmount);
        profitEarned = findViewById(R.id.profitEarned);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}