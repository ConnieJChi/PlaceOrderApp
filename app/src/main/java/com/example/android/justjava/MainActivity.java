package com.example.android.justjava;
//
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numOfCoffees = 0;
    public static final int PRICE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called to update quantity and price
     */
    public void update(View view) {
        display(numOfCoffees);
        displayPrice(calcPrice(numOfCoffees));
    }
    /**
     * This method is called to order
     */
    public void submitOrder(View view) {
        displayOrder();
        displayOrderTotal(calcPrice(numOfCoffees));
    }

    public int calcPrice(int quantity) {
        return numOfCoffees * PRICE;
    }

    /**
     * Increments number of coffees
     */
    public void increment(View view) {
        numOfCoffees += 1;
        update(view);
        resetOrder();
    }
    /**
     * Decrements number of coffees
     */
    public void decrement(View view) {
        numOfCoffees -= 1;
        if (numOfCoffees < 0) {
            numOfCoffees = 0;
        }
        update(view);
        resetOrder();
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayOrder() {
        TextView orderedTextView = findViewById(R.id.ordered_view);
        orderedTextView.setText("Order has been submitted! Thank you!");
    }

    private void resetOrder() {
        TextView orderedTextView = findViewById(R.id.ordered_view);
        orderedTextView.setText("");
        TextView totalTextView = findViewById(R.id.total_view);
        totalTextView.setText("");
    }
    private void displayOrderTotal(int num) {
        TextView orderedTextView = findViewById(R.id.total_view);
        orderedTextView.setText("Order total is: $" + num);
    }

}