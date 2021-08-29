package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetGoalOfElectricity extends AppCompatActivity {

    private EditText goalAmount;
    private EditText electricityBill;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal_of_electricity);

        goalAmount = findViewById(R.id.goalAmount);
        electricityBill = findViewById(R.id.electricityBill);
        button = findViewById(R.id.buttonSetGoalOfElectricity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}