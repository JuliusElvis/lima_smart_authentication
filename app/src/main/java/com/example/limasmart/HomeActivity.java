package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    CardView animalCalendar,vetReg,healthCheck,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vetReg = findViewById(R.id.vetReg);
        animalCalendar = findViewById(R.id.animalCalendar);

        vetReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        animalCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openCalendarActivity();
            }
        });


    }

    public void openActivity(){
            Intent intent = new Intent(this,DocDisplayActivity.class);
            startActivity(intent);
    }

    public void openCalendarActivity(){
        Intent intent = new Intent(this,calendarActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}