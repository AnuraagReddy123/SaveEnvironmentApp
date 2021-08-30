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
import model.SellingObject;

public class BuyAndSellActivity extends AppCompatActivity {

    private ArrayList<SellingObject> productArrayList;
    private BuyAndSellAdapter buyAndSellAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_and_sell);

        productArrayList = new ArrayList<>();
        productArrayList.add(new SellingObject("Kishor", "Gaddam", "8793593531", "NO DESCRIPTION", "120"));

        recyclerView = findViewById(R.id.recyclerViewBuyAndSell);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addProduct = findViewById(R.id.addProduct);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyAndSellActivity.this, sellingProductDetails.class));
            }
        });

        buyAndSellAdapter = new BuyAndSellAdapter(BuyAndSellActivity.this, productArrayList);
        recyclerView.setAdapter(buyAndSellAdapter);
    }
}