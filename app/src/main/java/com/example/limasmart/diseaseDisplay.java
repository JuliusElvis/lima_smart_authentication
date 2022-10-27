package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class diseaseDisplay extends AppCompatActivity {
    TextView tvDisease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_display);

        tvDisease = findViewById(R.id.textView24);

        String disease = getIntent().getExtras().getString("disease");

        tvDisease.setText(disease);

    }
}