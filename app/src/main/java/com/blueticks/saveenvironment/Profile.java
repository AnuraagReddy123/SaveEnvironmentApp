package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private TextView name;
    private TextView saved;
    private TextView goal;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.profile_name);
        saved = findViewById(R.id.profile_saved);
        goal = findViewById(R.id.profile_goal);
        phone = findViewById(R.id.profile_phone_number);
    }
}