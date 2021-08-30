package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Set;

import model.User;
import util.UserApi;

public class SetGoalOfElectricity extends AppCompatActivity {

    private static final String LOG_TAG = SetGoalOfElectricity.class.getSimpleName();
    private EditText goalAmount;
    private EditText electricityBill;
    private Button button;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal_of_electricity);

        goalAmount = findViewById(R.id.goalAmount);
        electricityBill = findViewById(R.id.electricityBill);
        button = findViewById(R.id.buttonSetGoalOfElectricity);
        db = FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG,"Fetched the user data");
                String phoneNumber = UserApi.getInstance().getPhoneNumber();
                double electricBill = Double.parseDouble(electricityBill.getText().toString());
                double targetMoney = Double.parseDouble(goalAmount.getText().toString());
                updateUser(phoneNumber,electricBill,targetMoney);
            }
        });

    }

    private void updateUser(String phoneNumber,double electricityBill,double targetMoney) {
        db.collection("Users").document(UserApi.getInstance().getUserId())
                .update("targetMoney",targetMoney,"electricityBill",electricityBill)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }


}