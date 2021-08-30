package com.blueticks.saveenvironment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import model.User;
import util.UserApi;

public class Register extends AppCompatActivity {

    private static final String LOG_TAG = Register.class.getSimpleName();
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText phnNumber;
    private Button regButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initialise Firebase Auth
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phnNumber = findViewById(R.id.phoneNumber);
        regButton = findViewById(R.id.registerButton);
        db = FirebaseFirestore.getInstance();
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
                // get the user details from the edit text view
                String phoneNumber = phnNumber.getText().toString();
                String first_name = firstName.getText().toString();
                String last_name = lastName.getText().toString();
                db.collection("Users")
                        .whereEqualTo("phoneNumber",phoneNumber)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                // Check if our task successfully communicated from the server
                                if(task.isSuccessful()) {
                                    if(task.getResult().size() == 0) {
                                        // Call the VerifyNumberClass and pass phone number to it
                                        Intent intent = new Intent(Register.this, VerifyPhoneNumber.class);
                                        intent.putExtra("phoneNumber", phoneNumber);
                                        intent.putExtra("firstName",first_name);
                                        intent.putExtra("lastName",last_name);
                                        intent.putExtra("isSignIn",false);// is the user being signed in
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(Register.this, "Phone Number already exists", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Log.v(LOG_TAG,"There was an error while querying");
                                }
                            }
                        });
            }
        });

    }
}