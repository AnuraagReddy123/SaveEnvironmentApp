package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SavingMethods extends AppCompatActivity {
    private Button transportButton;
    private Button electricityButton;
    private Button button3;
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_methods);
        transportButton = findViewById(R.id.transportButton);
        electricityButton = findViewById(R.id.electricityButton);
        button3  = findViewById(R.id.plasticUsageButton);
        button4  = findViewById(R.id.button5);

        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SavingMethods.this, "Clickable", Toast.LENGTH_SHORT).show();
            }
        });
        electricityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SavingMethods.this, "Clickable", Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SavingMethods.this, "Clickable", Toast.LENGTH_SHORT).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SavingMethods.this, "Clickable", Toast.LENGTH_SHORT).show();
            }
        });
    }
}