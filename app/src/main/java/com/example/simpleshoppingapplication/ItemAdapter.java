package com.example.simpleshoppingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<String> items;
    private ArrayList<String> prices;
    private ArrayList<String> descriptions;
    private ArrayList<String> categories;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, ArrayList<String> items, ArrayList<String> prices, ArrayList<String> descriptions, ArrayList<String> categories) {
        this.items = items;
        this.prices = prices;
        this.descriptions = descriptions;
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_listview_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(items.get(position));
        holder.descriptionTextView.setText(descriptions.get(position));
        holder.priceTextView.setText(prices.get(position));

        // Set the appropriate icon based on the category
        switch (categories.get(position)) {
            case "food":
                holder.categoryImageView.setImageResource(R.drawable.food);
                break;
            case "electronics":
                holder.categoryImageView.setImageResource(R.drawable.electronic);
                break;
            case "hygiene":
                holder.categoryImageView.setImageResource(R.drawable.hygiene);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        ImageView categoryImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            categoryImageView = itemView.findViewById(R.id.categoryImageView);
        }
    }
}