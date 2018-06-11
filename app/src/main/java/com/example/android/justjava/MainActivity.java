package com.example.android.justjava;
//
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int numOfCoffees = 0;
    boolean pChecked = false;
    boolean rChecked = false;
    public static final int PRICE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void update(View view) {
        display(numOfCoffees);
        displayPrice(calcPrice());
    }

    public void submitOrder(View view) {
        displayOrder();
        displayOrderTotal(calcPrice());
        displayName();
        displayProduct();
        displayShipping();

//        Intent i = new Intent(Intent.ACTION_VIEW);

    }

    public int calcPrice() {
        if (pChecked) {
            return numOfCoffees * PRICE + 3;
        } else {
            return numOfCoffees * PRICE;
        }
    }

    public void increment(View view) {
        numOfCoffees += 1;
        update(view);
        reset();
    }

    public void decrement(View view) {
        numOfCoffees -= 1;
        if (numOfCoffees < 0) {
            numOfCoffees = 0;
        }
        update(view);
        reset();
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayOrder() {
        TextView orderedTextView = findViewById(R.id.ordered_view);
        EditText name = findViewById(R.id.name_get);
        if ((rChecked && pChecked) || (!rChecked && !pChecked))  {
            orderedTextView.setText("Check proper shipping options!");
        } else if ( numOfCoffees <= 0) {
            orderedTextView.setText("Please enter a number greater than 0!");
        } else if (name.getText().toString().isEmpty()) {
            orderedTextView.setText("Please enter a proper name!");
        } else if (numOfCoffees > 0) {
            orderedTextView.setText("Order has been submitted! Thank you!");
        }
    }

    private void displayName() {
        TextView name = findViewById(R.id.name_view);
        EditText nameGet = findViewById(R.id.name_get);
        if (!nameGet.getText().toString().isEmpty()) {
            name.setText("Name: " + nameGet.getText());
        } else {
            name.setText("Please enter a name!");
        }
    }

    private void displayProduct() {
        TextView productName = findViewById(R.id.productname_view);
        EditText productGet = findViewById(R.id.product_name_get);
        if (!productGet.getText().toString().isEmpty()) {
            productName.setText("Product: " + productGet.getText());
        } else {
            productName.setText("Please enter a product name!");
        }
    }
//    private void uncheck() {
//        CheckBox p = findViewById(R.id.checkbox_premium);
//        CheckBox r = findViewById(R.id.checkbox_regular);
//        p.setChecked(false);
//        r.setChecked(false);
//    }
    private void reset() {
        TextView orderedTextView = findViewById(R.id.ordered_view);
        orderedTextView.setText("");
        TextView totalTextView = findViewById(R.id.total_view);
        totalTextView.setText("");
        TextView name = findViewById(R.id.name_view);
        name.setText("");
        TextView shipping = findViewById(R.id.shipping_view);
        shipping.setText("");
        TextView product = findViewById(R.id.productname_view);
        product.setText("");

    }
    private void displayOrderTotal(int num) {
        TextView orderedTextView = findViewById(R.id.total_view);
        if (numOfCoffees > 0) {
            orderedTextView.setText("Order total is: $" + num);
        }
    }
    public void premiumClicked(View view) {
        reset();
//        CheckBox p = findViewById(R.id.checkbox_premium);
        CheckBox p = (CheckBox) view;
        if (p.isChecked()) {
            pChecked = true;
        } else {
            pChecked = false;
        }
    }
    public void regularClicked(View view) {
        reset();
//        CheckBox r = findViewById(R.id.checkbox_regular);
        CheckBox r = (CheckBox) view;
        if (r.isChecked()) {
            rChecked = true;
        } else {
            rChecked = false;
        }
    }
    public void displayShipping() {
        TextView shipping = findViewById(R.id.shipping_view);
        if (rChecked && pChecked) {
            shipping.setText("Choose one shipping option!");
        } else if (rChecked) {
            shipping.setText("Regular Shipping!");
        } else if (pChecked) {
            shipping.setText("Premium Shipping! Extra $3 cost. ");
        } else {
            shipping.setText("Choose an option please!");
        }

    }
    }