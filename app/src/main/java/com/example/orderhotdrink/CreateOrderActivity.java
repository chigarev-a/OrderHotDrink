package com.example.orderhotdrink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private int DRINK_TEA_ID = 1;
    private int DRINK_COFFEE_ID = 2;

    private TextView textViewGreeting;
    private TextView textViewAdditives;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private CheckBox checkBoxLemon;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;

    private String name = "User";
    private int selectedDrink = DRINK_TEA_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();
        if(intent.hasExtra("name")){
            name = intent.getStringExtra("name");
        }

        textViewGreeting = findViewById(R.id.textViewGreeting);
        textViewGreeting.setText(String.format(getResources().getString(R.string.greeting), name));
        textViewAdditives = findViewById(R.id.textViewAdditives);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);

        if(savedInstanceState != null){
            selectedDrink = savedInstanceState.getInt("selectedDrink");
        }

        if(selectedDrink == DRINK_TEA_ID){
            selectedTea();
        }else if(selectedDrink == DRINK_COFFEE_ID){
            selectedCoffee();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedDrink", selectedDrink);
    }

    public void onClickChangeDrink(View view) {
        switch(view.getId()){
            case R.id.rbTea :
                selectedTea();
                break;
            case R.id.rbCoffee :
                selectedCoffee();
                break;
        }
    }

    private void selectedTea(){
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
        checkBoxLemon.setVisibility(View.VISIBLE);
        selectedDrink = DRINK_TEA_ID;
        textViewAdditives.setText(String.format( getString(R.string.text_view_additives), "Чай"));
    }
    private void selectedCoffee(){
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
        checkBoxLemon.setVisibility(View.INVISIBLE);
        checkBoxLemon.setChecked(false);
        selectedDrink = DRINK_COFFEE_ID;
        textViewAdditives.setText(String.format( getString(R.string.text_view_additives), "Кофе"));

    }

    public void onClickCreateOrder(View view) {
        String drink = getResources().getString(R.string.tea);
        String typeDrink = " ";
        if(selectedDrink == DRINK_TEA_ID){
            drink = getResources().getString(R.string.tea);
            typeDrink = spinnerTea.getSelectedItem().toString();
        }else if(selectedDrink == DRINK_COFFEE_ID){
            drink = getResources().getString(R.string.coffee);
            typeDrink = spinnerCoffee.getSelectedItem().toString();
        }
        StringBuilder additives = new StringBuilder();
        if(checkBoxMilk.isChecked()){
            additives.append(getResources().getText(R.string.cbMilk)).append(" ");
        }
        if(checkBoxSugar.isChecked()){
            additives.append(getResources().getText(R.string.cbSugar)).append(" ");
        }
        if(checkBoxLemon.isChecked()){
            additives.append(getResources().getText(R.string.cbLemon)).append(" ");
        }

        String order = "Напиток: " + drink + "\n" + "Тип напитка: " + typeDrink + "\n" + "Добавки: " + additives;

        Log.d("tag1", order);

        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}