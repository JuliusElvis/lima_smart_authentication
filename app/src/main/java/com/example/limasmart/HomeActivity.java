package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    CardView animalCalendar,vetReg,healthCheck,more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vetReg = findViewById(R.id.vetReg);
        animalCalendar = findViewById(R.id.animalCalendar);
        healthCheck = findViewById(R.id.healthCheck);

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

        healthCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHealthActivity();
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

    public void openHealthActivity(){
        Intent intent = new Intent(this,HealthActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}