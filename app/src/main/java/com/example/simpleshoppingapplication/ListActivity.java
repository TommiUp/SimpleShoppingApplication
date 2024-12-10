package com.example.simpleshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    static ArrayList<String> items = new ArrayList<>();
    static ArrayList<String> prices = new ArrayList<>();
    static ArrayList<String> descriptions = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add dividers to RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        productRecyclerView.addItemDecoration(dividerItemDecoration);

        itemAdapter = new ItemAdapter(this, items, prices, descriptions, categories);
        productRecyclerView.setAdapter(itemAdapter);

        // Check if there is data from AddActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name") && intent.hasExtra("description") && intent.hasExtra("price") && intent.hasExtra("category")) {
            addItemToList(intent.getStringExtra("name"), intent.getStringExtra("description"), intent.getStringExtra("price"), intent.getStringExtra("category"));
        }

        // Update the total price at the start
        updateTotalPrice();
    }

    private void addItemToList(String name, String description, String price, String category) {
        // Ensure price ends with €
        if (!price.endsWith("€")) {
            price += "€";
        }

        items.add(name);
        descriptions.add(description);
        prices.add(price);
        categories.add(category);

        itemAdapter.notifyDataSetChanged();
        updateTotalPrice(); // Update total price
    }

    private void updateTotalPrice() {
        double total = 0.0;
        for (String price : prices) {
            // Remove € symbol if present and convert to double
            String cleanedPrice = price.replace("€", "").trim();
            total += Double.parseDouble(cleanedPrice);
        }
        TextView totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText(String.format("Total: %.2f€", total));
    }
}