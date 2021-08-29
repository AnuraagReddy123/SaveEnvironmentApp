package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignInActivity extends AppCompatActivity {

    private TextView registerTxt;
    private TextInputEditText phnNumber;
    private Button signInButton;
    private FirebaseAuth mAuth;// firebase authentication object
    private FirebaseUser currentUser;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // initialising objects
        mAuth = FirebaseAuth.getInstance();
        mAuth.useAppLanguage();
        phnNumber = findViewById(R.id.phoneNumber_signIn);
        signInButton = findViewById(R.id.signInButton);
        registerTxt = findViewById(R.id.registerTxt);
        currentUser = mAuth.getCurrentUser();
        // check if the user is logged in
        if(currentUser != null) {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
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
                        // method for sending verification code
                    }
                    // get the phone number from the edit text view
                    String phoneNumber = phnNumber.getText().toString();
                    // Call the VerifyNumberClass and pass phone number to it
                    Intent intent = new Intent(SignInActivity.this,VerifyPhoneNumber.class);
                    intent.putExtra("phoneNumber",phoneNumber);
                    startActivity(intent);
                }
            });

        }
    }


}