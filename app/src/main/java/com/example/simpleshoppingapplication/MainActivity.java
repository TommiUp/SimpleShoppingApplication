package com.example.simpleshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find Views
        Button addActivityBtn = findViewById(R.id.addActivityBtn);
        Button listActivityBtn = findViewById(R.id.listActivitybtn);

        // Handle Add Product Click
        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(startIntent);
            }
        });

        // Handle Shopping List Click
        listActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(startIntent);
            }
        });
    }
}