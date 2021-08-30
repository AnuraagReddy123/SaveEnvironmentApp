package com.blueticks.saveenvironment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import model.Idea;

public class ideaShareActivity extends AppCompatActivity {

    // Adding firebase stuff
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Ideas");

    private ArrayList<Idea> ideaArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_share);

        ideaArrayList = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        progressBar = findViewById(R.id.idea_share_progress);
        scrollView = findViewById(R.id.mainView);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addIdea = findViewById(R.id.addIdea);

        addIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ideaShareActivity.this, textOfIdea.class));
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(ideaShareActivity.this, ideaArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        collectionReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            ideaArrayList.clear();
                            for (QueryDocumentSnapshot ideas: queryDocumentSnapshots) {
                                Idea idea = ideas.toObject(Idea.class);
                                ideaArrayList.add(idea);
                            }

                            // Invoke recycler view
                            recyclerViewAdapter = new RecyclerViewAdapter(ideaShareActivity.this,
                                    ideaArrayList);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        } else {

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Checking", "onFailure: " + e.getMessage());
                    }
                });
    }
}