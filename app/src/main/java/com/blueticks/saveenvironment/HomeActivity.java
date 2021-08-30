package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import util.UserApi;

public class HomeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button transportationBtn;
    private Button electricityBtn;
    private Button setGoalBtn;
    private Button additionalInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressBar);
        transportationBtn = findViewById(R.id.transportation_btn);
        electricityBtn = findViewById(R.id.electricity_btn);
        setGoalBtn = findViewById(R.id.setgoal_btn);
        additionalInfoBtn = findViewById(R.id.additional_btn);

        // if no user is currently logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(HomeActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
        }
        transportationBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, TransportActivity.class)));

        electricityBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ElectricityActivity.class)));

        setGoalBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, SetGoalOfElectricity.class)));

        additionalInfoBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ideaShareActivity.class)));
    }
}