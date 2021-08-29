package com.blueticks.saveenvironment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Idea;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Idea> ideaList;

    public RecyclerViewAdapter(Context context, List<Idea> ideaList) {
        this.context = context;
        this.ideaList = ideaList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Idea idea = ideaList.get(position);
        String txt = idea.getFirstName().toString() + " " + idea.getLastName().toString();
        holder.name.setText(txt);
        holder.ideaText.setText(idea.getText());
    }

    @Override
    public int getItemCount() {
        return ideaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView ideaText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            ideaText = itemView.findViewById(R.id.ideaText);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
