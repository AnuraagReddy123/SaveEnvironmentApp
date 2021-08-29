package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.*;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText phnNumber;
    private TextInputEditText password;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phnNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        regButton = findViewById(R.id.registerButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstName.getText().toString().trim().length() == 0){
                    firstName.setError("Please enter your first name");
                }else{
                    firstName.setError(null);
                }

                if(lastName.getText().toString().trim().length() == 0){
                    lastName.setError("Please enter your last name");
                }else{
                    lastName.setError(null);
                }

                if(android.text.TextUtils.isDigitsOnly(phnNumber.getText().toString().trim()) == false || phnNumber.getText().toString().trim().length() != 10){
                    phnNumber.setError("Please enter correct phone number");
                }else{
                    phnNumber.setError(null);
                }

                if(password.getText().toString().trim().length() == 0){
                    password.setError("Please enter password");
                }else{
                    password.setError(null);
                }
            }
        });

    }
}