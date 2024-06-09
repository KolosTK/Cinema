package com.example.cinema.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinema.R;

public class LikedActivity extends AppCompatActivity {
    private ImageView backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        initView();
    }
    private void initView() {
        backImage = findViewById(R.id.arrow3);
        backImage.setOnClickListener(v -> finish());

    }
}
