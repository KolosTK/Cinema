package com.example.cinema.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cinema.R;

public class SeatsActivity extends AppCompatActivity {
    private ImageView backButton;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14;
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
