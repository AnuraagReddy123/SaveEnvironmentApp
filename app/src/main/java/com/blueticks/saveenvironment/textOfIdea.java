package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class textOfIdea extends AppCompatActivity {

    private EditText ideaContent;
    private Button postBtn;
    private LinearLayout cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_of_idea);

        ideaContent = findViewById(R.id.ideaContent);
        postBtn = findViewById(R.id.postButton);
        cardsList = findViewById(R.id.cardsView);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(textOfIdea.this, ideaShareActivity.class));
            }
        });

    }
}