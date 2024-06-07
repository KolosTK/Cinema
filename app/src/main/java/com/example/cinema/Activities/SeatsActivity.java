package com.example.cinema.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinema.R;

public class SeatsActivity extends AppCompatActivity {
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seats);
//        idFilm=getIntent().getIntExtra("id",0);
        initView();
//        sendRequest();
    }
    private void initView() {
        backButton = findViewById(R.id.arrow);
        backButton.setOnClickListener(v -> finish());
    }
}
