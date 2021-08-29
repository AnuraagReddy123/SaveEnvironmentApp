package com.blueticks.saveenvironment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {

    private static final String LOG_TAG = SignInActivity.class.getSimpleName();
    private TextView registerTxt;
    private TextInputEditText phnNumber;
    private Button signInButton;
    private FirebaseAuth mAuth;// firebase authentication object
    private FirebaseUser currentUser;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // initialising objects
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
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
                    String phoneNumber = phnNumber.getText().toString();
                    db.collection("Users")
                            .whereEqualTo("phoneNumber",phoneNumber)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    // Check if our task successfully communicated from the server
                                    if(task.isSuccessful()) {
                                        if(task.getResult().size() == 0) {
                                            Toast.makeText(SignInActivity.this, "Phone Number doesn't exist", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Intent intent = new Intent(SignInActivity.this, VerifyPhoneNumber.class);
                                            intent.putExtra("phoneNumber", phoneNumber);
                                            intent.putExtra("isSignIn",true);// is the user being signed in
                                            startActivity(intent);
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


}