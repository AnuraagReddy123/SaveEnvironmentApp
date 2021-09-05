package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import util.UserApi;

public class SettingsActivity extends AppCompatActivity {

    public static final String LOG_TAG = SettingsActivity.class.getSimpleName();
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch notIfSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        notIfSwitch = findViewById(R.id.switch1);
        notIfSwitch.setChecked(UserApi.getInstance().getSubscribed());
        notIfSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                UserApi.getInstance().setSubscribed(!UserApi.getInstance().getSubscribed());
                if(isChecked) {
                    HomeActivity.setUpNotifications();
                }
                else{
                    HomeActivity.mWorkManager = null;
                }
            }
        });
    }
}