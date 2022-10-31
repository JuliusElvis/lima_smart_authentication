package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class profileActivity extends AppCompatActivity {
    TextView tvName, tvAddress, tvPhone, tvLocality;

    String phoneno,locality;
    Button call;

    String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.textViewName);
        tvAddress = findViewById(R.id.textViewAddress);
        tvPhone = findViewById(R.id.phonetv);
        tvLocality = findViewById(R.id.localitytextView);

        String name = getIntent().getExtras().getString("username");
        String address = getIntent().getExtras().getString("address");



        tvName.setText(name);
        tvAddress.setText(address);
        phoney(name);
        loc(name);

        String s = String.valueOf(phone.length());
        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();



    }

    public void openPhone(String number){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    public void phoney(String name){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://10.8.122.87/project1/getPhone.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        phoneno = String.valueOf(putData.getResult());
                        if (!phoneno.equals("")){
                            tvPhone.setText(phoneno);
                            tvPhone.setTextColor(Color.BLUE);
                            phone = phoneno;
                            if (phone.length()>5){
                                tvPhone.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        openPhone(phone);
                                    }
                                });
                            }
                        }else{
                            tvPhone.setText(getResources().getString(R.string.Phone));
                        }

                    }

                }}
        });

    }

    public void loc(String name){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "name";
                //Creating array for data
                String[] data = new String[1];
                data[0] = name;

                PutData putData = new PutData("http://10.8.122.87/project1/getLoc.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        locality = String.valueOf(putData.getResult());
                        if (!locality.equals("")){
                            tvLocality.setText(locality);
                        }else{
                            tvLocality.setText(getResources().getString(R.string.locality));
                        }


                    }

                }}
        });

    }
}