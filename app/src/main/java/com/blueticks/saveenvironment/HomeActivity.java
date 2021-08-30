package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import model.User;
import util.UserApi;

public class HomeActivity extends AppCompatActivity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    private ProgressBar progressBar;
    private Button transportationBtn;
    private Button electricityBtn;
    private Button setGoalBtn;
    private Button additionalInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressBar);
        transportationBtn = findViewById(R.id.transportation_btn);
        electricityBtn = findViewById(R.id.electricity_btn);
        setGoalBtn = findViewById(R.id.setgoal_btn);
        additionalInfoBtn = findViewById(R.id.additional_btn);

        // if no user is currently logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(HomeActivity.this, "Welcome!!", Toast.LENGTH_SHORT).show();
            // when the user is logged in
            UserApi.getInstance().setUserid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(UserApi.COLLECTIONS_NAME).document(UserApi.getInstance().getUserId())
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        Log.d(LOG_TAG,"Data fetched successfully");
                        DocumentSnapshot documentSnapshot = task.getResult();
                        User user = documentSnapshot.toObject(User.class);
                        UserApi userApi = UserApi.getInstance();
                        userApi.setFirstName(user.getFirstName());
                        userApi.setLastName(user.getLastName());
                        userApi.setPhoneNumber(user.getPhoneNumber());
                        userApi.setElectricityBill(user.getElectricityBill());
                        userApi.setTargetMoney(user.getTargetMoney());
                        userApi.setCurrentMoney(user.getCurrentMoney());
                        double currentMoney = UserApi.getInstance().getCurrentMoney();
                        double targetMoney = UserApi.getInstance().getTargetMoney();
                        progressBar.setProgress((int)(currentMoney/targetMoney));
                    }
                    else{
                        Log.d(LOG_TAG,"Failed to fetch data");
                    }
                }
            });
        }
        transportationBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, TransportActivity.class)));

        electricityBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ElectricityActivity.class)));

        setGoalBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, SetGoalOfElectricity.class)));

        additionalInfoBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ideaShareActivity.class)));
    }
}