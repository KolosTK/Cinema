package com.example.cinema.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cinema.R;

public class SeatsActivity extends AppCompatActivity {
    private ImageView backButton;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,seatsButton;
    private TextView seatsAmount, costAmount;
    private int seatsCount,totalCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);

        setupButton(findViewById(R.id.button1), 100);
        setupButton(findViewById(R.id.button2), 250);
        setupButton(findViewById(R.id.button3), 250);
        setupButton(findViewById(R.id.button4), 100);
        setupButton(findViewById(R.id.button5), 100);
        setupButton(findViewById(R.id.button6), 100);
        setupButton(findViewById(R.id.button7), 200);
        setupButton(findViewById(R.id.button8), 200);
        setupButton(findViewById(R.id.button9), 250);
        setupButton(findViewById(R.id.button10), 250);
        setupButton(findViewById(R.id.button11), 300);
        setupButton(findViewById(R.id.button12), 300);
        setupButton(findViewById(R.id.button13), 200);
        setupButton(findViewById(R.id.button14), 200);

        seatsAmount = findViewById(R.id.seatsAmount);
        costAmount = findViewById(R.id.costAmount);

        initView();
        setupSeatsButtons();
    }

    private void setupSeatsButtons() {
        // Setup individual seat buttons
        setupButton(findViewById(R.id.button1), 100);
        setupButton(findViewById(R.id.button2), 250);
        // Add the rest of the buttons...

        seatsButton = findViewById(R.id.seatsButton);
        seatsButton.setOnClickListener(v -> {
            resetBooking();
        });
    }
    private void resetBooking() {
        seatsCount = 0;
        totalCost = 0;
        seatsAmount.setText(String.valueOf(seatsCount));
        costAmount.setText(String.valueOf(totalCost));

        // Show a message to the user
        Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();

        // Optionally reset all seat buttons to unselected state
        resetSeatButtons();
    }

    private void resetSeatButtons() {
        // You need to reset the background for all the seat buttons as well
        int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button10,R.id.button11,R.id.button12,R.id.button13, R.id.button14};
        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            button.setSelected(false);
            button.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        }
    }

    private void setupButton(Button button, int seatCost) {
        button.setOnClickListener(view -> {
            button.setSelected(!button.isSelected()); // Toggle selection state
            if (button.isSelected()) {
                button.setBackground(ContextCompat.getDrawable(SeatsActivity.this, R.drawable.button_background_empty));
                seatsCount++;
                totalCost += seatCost;
            } else {
                button.setBackground(ContextCompat.getDrawable(SeatsActivity.this,  R.drawable.button_background));
                seatsCount--;
                totalCost -= seatCost;
            }
            seatsAmount.setText(String.valueOf(seatsCount));
            costAmount.setText(String.valueOf(totalCost));
        });
    }

    private void initView() {
        backButton = findViewById(R.id.arrow);
        backButton.setOnClickListener(v -> finish());
    }
}
