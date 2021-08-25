package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

    private TextView registerTxt;
    private TextInputEditText phnNumber;
    private TextInputEditText password;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phnNumber = findViewById(R.id.phoneNumber_signIn);
        password = findViewById(R.id.password_signIn);
        signInButton = findViewById(R.id.signInButton);

        registerTxt = findViewById(R.id.registerTxt);
        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, Register.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phnNumber.getText().toString().trim().length() == 0){
                    phnNumber.setError("Please enter your phone number");
                }else{
                    phnNumber.setError(null);
                }

                if(password.getText().toString().trim().length() == 0){
                    password.setError("Please your enter password");
                }else{
                    password.setError(null);
                }
            }
        });
    }
}