package com.example.orderhotdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView textViewOrderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_order_details);
        textViewOrderDetails = findViewById(R.id.textViewOrderDetails);
        textViewOrderDetails.setText(intent.getStringExtra("order"));
    }
}