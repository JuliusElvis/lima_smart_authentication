package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {
    TextView tvName, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.textViewName);
        tvAddress = findViewById(R.id.textViewAddress);

        String s = getIntent().getExtras().getString("username");
        String s1 = getIntent().getExtras().getString("address");

        tvName.setText(s);
        tvAddress.setText(s1);
    }
}