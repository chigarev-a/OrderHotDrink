package com.example.orderhotdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editTextName;
    private EditText editTextPassword;
    private String name;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        textView = findViewById(R.id.textView2);
    }

    public void onClickCreateOrder(View view) {
        name = editTextName.getText().toString();
        pass = editTextPassword.getText().toString();
        if(name.isEmpty() || !pass.equals("555")){
            Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}