package com.blueticks.saveenvironment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.SellingObject;

public class BuyAndSellAdapter extends RecyclerView.Adapter<BuyAndSellAdapter.ViewHolder1> {
    private Context context;
    private List<SellingObject> productList;

    public BuyAndSellAdapter(Context context, List<SellingObject> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_and_sell_card, parent, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        SellingObject sellObj = productList.get(position);
        String txt = sellObj.getFirstName().toString()+ " " + sellObj.getLastName().toString();
        holder.name1.setText(txt);
        holder.phnNumber.setText("Mobile Number: "+sellObj.getPhoneNumber().toString());
        holder.description.setText(sellObj.getProductDescription());
        holder.price.setText(sellObj.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name1, phnNumber, description, price;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name1);
            phnNumber = itemView.findViewById(R.id.phnNumber_buyAndSell);
            description = itemView.findViewById(R.id.productDescription);
            price = itemView.findViewById(R.id.price);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
