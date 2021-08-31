package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private TextView percentCompleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progressBar);
        transportationBtn = findViewById(R.id.transportation_btn);
        electricityBtn = findViewById(R.id.electricity_btn);
        setGoalBtn = findViewById(R.id.setgoal_btn);
        additionalInfoBtn = findViewById(R.id.additional_btn);
        percentCompleteText = findViewById(R.id.percent_complete_text);

        // if no user is currently logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else {
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
                        userApi.setCurrentMoney(user.getCurrentMoney());
                        userApi.setTargetMoney(user.getTargetMoney());
                        userApi.setElectricityBill(user.getElectricityBill());
                        double currentMoney = UserApi.getInstance().getCurrentMoney();
                        double targetMoney = UserApi.getInstance().getTargetMoney();
                        if (targetMoney == 0) {
                            percentCompleteText.setText("Set a goal!");
                        } else {
                            int percent = (int)((currentMoney * 100.0f)/targetMoney);
                            Log.d(LOG_TAG,"CurrentMoney: " + currentMoney);
                            Log.d(LOG_TAG,"TargetMoney: " + targetMoney);
                            Log.d(LOG_TAG,"Percent: " + percent);
                            percent = 45;
                            progressBar.setProgress(45);
                            percentCompleteText.setText(String.format("%s %d%s", getString(R.string.you_have_completed_string), percent, getString(R.string.percent_goal_string)));
                        }
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

    @Override
    protected void onResume() {
        super.onResume();
        // if no user is currently logged in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else {
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
                        userApi.setCurrentMoney(user.getCurrentMoney());
                        userApi.setTargetMoney(user.getTargetMoney());
                        userApi.setElectricityBill(user.getElectricityBill());
                        double currentMoney = UserApi.getInstance().getCurrentMoney();
                        double targetMoney = UserApi.getInstance().getTargetMoney();
                        if (targetMoney == 0) {
                            percentCompleteText.setText("Set a goal!");
                        } else {
                            int percent = (int)((currentMoney * 100.0f)/targetMoney);
                            Log.d(LOG_TAG,"CurrentMoney: " + currentMoney);
                            Log.d(LOG_TAG,"TargetMoney: " + targetMoney);
                            Log.d(LOG_TAG,"Percent: " + percent);
                            progressBar.setProgress(percent);
                            percentCompleteText.setText(String.format("%s %d%s", getString(R.string.you_have_completed_string), percent, getString(R.string.percent_goal_string)));
                        }
                    }
                    else{
                        Log.d(LOG_TAG,"Failed to fetch data");
                    }
                }
            });
        }
    }
}