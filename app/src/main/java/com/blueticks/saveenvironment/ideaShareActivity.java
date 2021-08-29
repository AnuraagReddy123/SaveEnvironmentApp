package com.blueticks.saveenvironment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Idea;

public class ideaShareActivity extends AppCompatActivity {

    private ArrayList<Idea> ideaArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_share);
        ideaArrayList = new ArrayList<>();
        Idea idea = new Idea("Kishor", "Gaddam", "No Idea as of now.....");
        ideaArrayList.add(idea);

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
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String idea = intent.getStringExtra("addedIdea");
        ideaArrayList.add(new Idea("Abcde", "Fghijk", idea));
        recyclerViewAdapter = new RecyclerViewAdapter(ideaShareActivity.this, ideaArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}