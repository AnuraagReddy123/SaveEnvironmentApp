package com.blueticks.saveenvironment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

import model.User;

public class VerifyPhoneNumber extends AppCompatActivity {
    private static final String LOG_TAG = SignInActivity.class.getSimpleName();
    private Button verifyButton;
    private EditText phoneNumberEnteredByTheUser;
    private FirebaseAuth mAuth;
    private String verificationCodeBySystem;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean oldUser;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);
        verifyButton = findViewById(R.id.verify_btn);
        verifyButton.setOnClickListener(v -> {
            String code = phoneNumberEnteredByTheUser.getText().toString();
            if(code.isEmpty() || code.length() < 6) {
                phoneNumberEnteredByTheUser.setError("Wrong OTP...");
                phoneNumberEnteredByTheUser.requestFocus();
                return;
            }
            verifyCode(code);
        });
        phoneNumberEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        String phoneNumberWithCountry = "+91" + phoneNumber;
        oldUser = getIntent().getBooleanExtra("isSignIn",false);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        // send the OTP as soon as this activity is started
        sendVerificationCode(phoneNumberWithCountry);
    }

    private void sendVerificationCode(String phoneNumberWithCountry) {
        // the method sends verification code
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumberWithCountry) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // the callback to detect the verification status
    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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
                        task -> {
                            if(task.isSuccessful()) {
                                if(!oldUser) {
                                    // add this new user to that database
                                    addDataToFireStore(firstName,lastName,phoneNumber);
                                }
                                else{
                                    Intent intent = new Intent(VerifyPhoneNumber.this,
                                            HomeActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else {
                                Toast.makeText(VerifyPhoneNumber.this,
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void addDataToFireStore(String firstName,String lastName,String phoneNumber) {
        // creating a collection reference
        // for our Firebase Firestore database
        CollectionReference dbUsers = db.collection("Users");
        // Create the user object with the user data
        User user = new User(firstName,lastName, phoneNumber);
        // add the data to the database
        dbUsers.
            add(user).
            addOnSuccessListener(documentReference -> {
                // when the user has been added successfully, we display a message
                Toast.makeText(VerifyPhoneNumber.this,"You have been registered successfully",Toast.LENGTH_SHORT).show();
                // redirect the user to the home activity
                Intent intent = new Intent(VerifyPhoneNumber.this,
                        HomeActivity.class);
                startActivity(intent);
            }).addOnFailureListener(e -> {
            // when user couldn't be added to the database
            Toast.makeText(VerifyPhoneNumber.this,"An Error Occurred",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(VerifyPhoneNumber.this,
                    SignInActivity.class);
            startActivity(intent);
        });
    }
}