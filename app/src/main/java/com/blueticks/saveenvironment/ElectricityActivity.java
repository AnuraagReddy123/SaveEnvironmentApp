package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ElectricityActivity extends AppCompatActivity {

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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}