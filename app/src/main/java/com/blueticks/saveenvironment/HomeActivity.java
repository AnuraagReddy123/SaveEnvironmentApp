package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class HomeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button transportationBtn;
    private Button electricityBtn;
    private Button additionalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.progressBar);
        transportationBtn = findViewById(R.id.transportation_btn);
        electricityBtn = findViewById(R.id.electricity_btn);

        transportationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        electricityBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ElectricityActivity.class)));
    }
}