package com.blueticks.saveenvironment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText phnNumber;
    private Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialise Firebase Auth
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phnNumber = findViewById(R.id.phoneNumber);
        regButton = findViewById(R.id.registerButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(firstName.getText()).toString().trim().length() == 0) {
                    firstName.setError("Please enter your first name");
                } else {
                    firstName.setError(null);
                }

                if (Objects.requireNonNull(lastName.getText()).toString().trim().length() == 0) {
                    lastName.setError("Please enter your last name");
                } else {
                    lastName.setError(null);
                }

                if (!android.text.TextUtils.isDigitsOnly(Objects.requireNonNull(phnNumber.getText()).toString().trim()) || phnNumber.getText().toString().trim().length() != 10) {
                    phnNumber.setError("Please enter correct phone number");
                } else {
                    phnNumber.setError(null);
                }
                // get the phone number from the edit text view
                String phoneNumber = phnNumber.getText().toString();
                // Call the VerifyNumberClass and pass phone number to it
                Intent intent = new Intent(Register.this, VerifyPhoneNumber.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

    }
}