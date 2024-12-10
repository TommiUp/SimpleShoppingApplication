package com.example.simpleshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Find Views
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        EditText priceEditText = findViewById(R.id.priceEditText);
        RadioGroup categoryRadioGroup = findViewById(R.id.categoryRadioGroup);
        Button addBtn = findViewById(R.id.addBtn);

        // Handle Add Button Click
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();
                String price = priceEditText.getText().toString().trim();

                // Get selected category
                int selectedCategoryId = categoryRadioGroup.getCheckedRadioButtonId();
                String category = "";

                if (selectedCategoryId == R.id.foodRadioBtn) {
                    category = "food";
                } else if (selectedCategoryId == R.id.electronicRadioBtn) {
                    category = "electronics";
                } else if (selectedCategoryId == R.id.hygieneRadioBtn) {
                    category = "hygiene";
                }

                // Check for empty fields or no selection
                if (name.isEmpty() || description.isEmpty() || price.isEmpty() || category.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill all fields and choose a category", Toast.LENGTH_SHORT).show();
                } else {
                    // Ensure price ends with "€"
                    if (!price.endsWith("€")) {
                        price += "€";
                    }

                    // Pass data back to ListActivity
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("description", description);
                    intent.putExtra("price", price);
                    intent.putExtra("category", category);

                    // Add item to static lists in ListActivity
                    ListActivity.items.add(name);
                    ListActivity.descriptions.add(description);
                    ListActivity.prices.add(price);
                    ListActivity.categories.add(category);

                    // Finish Activity
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}