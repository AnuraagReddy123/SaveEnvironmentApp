package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SetGoal extends AppCompatActivity {
    private TextView textView;
    private TextInputLayout amountTextInputLayout;
    private Button setGoalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        textView = findViewById(R.id.setGoalTextView);
        amountTextInputLayout = findViewById(R.id.amountTextInputLayout);
        setGoalButton = findViewById(R.id.setGoalButton);

        Objects.requireNonNull(amountTextInputLayout.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setGoalButton.setEnabled(!amountTextInputLayout.getEditText().getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SetGoal.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
//        startActivity(new Intent(SetGoal.this,SavingMethods.class));
    }
}