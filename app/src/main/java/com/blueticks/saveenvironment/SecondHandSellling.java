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
import com.google.firebase.firestore.FirebaseFirestore;

import util.UserApi;

public class SecondHandSellling extends AppCompatActivity {

    public static final String LOG_TAG = SecondHandSellling.class.getSimpleName();
    private EditText soldAmount;
    private EditText profitEarned;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_hand_sellling);

        profitEarned = findViewById(R.id.profitEarned);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSavedAmount(Double.parseDouble(profitEarned.getText().toString()));
            }
        });

    }

    // add the saved amount to the current saved amount of the user
    private void updateSavedAmount(Double moneySaved) {
        Double currentMoney = UserApi.getInstance().getCurrentMoney();// get the current money of the user
        Double newMoney = moneySaved + currentMoney;
        Log.d(LOG_TAG,"Money: " + newMoney);
        UserApi.getInstance().setCurrentMoney(newMoney);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // update the database
        db.collection(UserApi.COLLECTIONS_NAME).document(UserApi.getInstance().getUserId())
                .update(UserApi.CURRENT_MONEY,newMoney)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SecondHandSellling.this,"Data updated Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.v(LOG_TAG,"Current Money wasn't successfully saved");
                        Toast.makeText(SecondHandSellling.this,"Data update Failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}