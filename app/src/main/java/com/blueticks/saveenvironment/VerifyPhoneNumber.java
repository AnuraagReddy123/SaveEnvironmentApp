package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNumber extends AppCompatActivity {
    private static final String LOG_TAG = SignInActivity.class.getSimpleName();
    private Button verifyButton;
    private EditText phoneNumberEnteredByTheUser;
    private FirebaseAuth mAuth;
    private String verificationCodeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);
        verifyButton = findViewById(R.id.verify_btn);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = phoneNumberEnteredByTheUser.getText().toString();
                if(code.isEmpty() || code.length() < 6) {
                    phoneNumberEnteredByTheUser.setError("Wrong OTP...");
                    phoneNumberEnteredByTheUser.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });
        phoneNumberEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        String phoneNumber = "+91" + getIntent().getStringExtra("phoneNumber");
        mAuth = FirebaseAuth.getInstance();
        // send the OTP as soon as this activity is started
        sendVerificationCode(phoneNumber);
    }

    private void sendVerificationCode(String phoneNumber) {
        // the method sends verification code
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            // Getting the code sent my SMS
            String code = phoneAuthCredential.getSmsCode();
            // when the code is not detected automatically
            // the user has to enter the code
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Log.v(LOG_TAG,"Invalid Request");
            }
            else if (e instanceof FirebaseTooManyRequestsException) {
                Log.v(LOG_TAG,"The SMS quota for the project has been exceeded");
            }
            Toast.makeText(VerifyPhoneNumber.this,
                    "An error occurred.",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s,forceResendingToken);
            // get the code
            verificationCodeBySystem = s;
        }
    };

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,
                codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneNumber.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Intent intent = new Intent(VerifyPhoneNumber.this,
                                            HomeActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(VerifyPhoneNumber.this,
                                            task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}