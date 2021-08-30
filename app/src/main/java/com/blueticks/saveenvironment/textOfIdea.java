package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import model.Idea;
import util.UserApi;

public class textOfIdea extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Ideas");

    private EditText ideaContent;
    private Button postBtn;
    private LinearLayout cardsList;
    private ProgressBar progressBar;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_of_idea);

        ideaContent = findViewById(R.id.ideaContent);
        postBtn = findViewById(R.id.postButton);
        progressBar = findViewById(R.id.idea_progress_bar);
        layout = findViewById(R.id.ideaView);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add text written by user to the database
                saveIdea();
            }
        });

    }

    private void saveIdea() {
//        String firstName = UserApi.getInstance().getFirstName();
//        String lastName = UserApi.getInstance().getLastName();
        String firstName = UserApi.getInstance().getCurrentUser().getFirstName();
        String lastName = UserApi.getInstance().getCurrentUser().getLastName();
        String ideaFromUser = ideaContent.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(ideaFromUser)) {
            Idea idea = new Idea(firstName, lastName, ideaFromUser);
            collectionReference.add(idea)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            startActivity(new Intent(textOfIdea.this, ideaShareActivity.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Check", "onFailure: " + e.getMessage());
                            Toast.makeText(textOfIdea.this, "There was an error", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            progressBar.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            Toast.makeText(textOfIdea.this, "Please enter idea", Toast.LENGTH_SHORT).show();
        }
    }
}