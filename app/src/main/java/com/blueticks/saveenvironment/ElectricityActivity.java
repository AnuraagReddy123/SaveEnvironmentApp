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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import util.UserApi;

public class ElectricityActivity extends AppCompatActivity {

    private static final String LOG_TAG = ElectricityActivity.class.getSimpleName();
    private TextView prevBillText;
    private EditText newBillEt;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        prevBillText = findViewById(R.id.prev_bill_text);
        newBillEt = findViewById(R.id.new_bill_et);
        saveBtn = findViewById(R.id.save_btn);
        Double previousBill = UserApi.getInstance().getElectricityBill();
        Double currentMoney = UserApi.getInstance().getCurrentMoney();
        prevBillText.setText(previousBill.toString());
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double newBill = Double.parseDouble(newBillEt.getText().toString());
                UserApi.getInstance().setElectricityBill(newBill);
                Log.d(LOG_TAG,"Money: " + (currentMoney + previousBill - newBill));
                UserApi.getInstance().setCurrentMoney(currentMoney + previousBill - newBill);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection(UserApi.COLLECTIONS_NAME).document(UserApi.getInstance().getUserId())
                        .update(UserApi.CURRENT_MONEY,currentMoney + previousBill - newBill,UserApi.ELECTRICITY_BILL,newBill)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ElectricityActivity.this,"Data updated Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ElectricityActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.v(LOG_TAG,"Current Money wasn't successfully saved");
                                Toast.makeText(ElectricityActivity.this,"Data update Failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}