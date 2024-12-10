package com.example.simpleshoppingapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    // Variables for RecyclerView and data lists
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

        // Find RecyclerView and set layout manager
        productRecyclerView = findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add dividers to RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        productRecyclerView.addItemDecoration(dividerItemDecoration);

        // Initialize the adapter and set to RecyclerView
        itemAdapter = new ItemAdapter(this, items, prices, descriptions, categories);
        productRecyclerView.setAdapter(itemAdapter);

        // Check for data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name") && intent.hasExtra("description") && intent.hasExtra("price") && intent.hasExtra("category")) {
            addItemToList(intent.getStringExtra("name"), intent.getStringExtra("description"), intent.getStringExtra("price"), intent.getStringExtra("category"));
        }

        // Update total price
        updateTotalPrice();
    }

    private void addItemToList(String name, String description, String price, String category) {

        // € at the end of price
        if (!price.endsWith("€")) {
            price += "€";
        }

        // Add data
        items.add(name);
        descriptions.add(description);
        prices.add(price);
        categories.add(category);

        // Notify adapter and update total price
        itemAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    // Method to calculate and display total price
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