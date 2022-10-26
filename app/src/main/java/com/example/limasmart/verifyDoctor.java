package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class verifyDoctor extends AppCompatActivity {
    EditText regNo;
    Button button;
    public String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_doctor);

        regNo = findViewById(R.id.verifyReg);
        button = findViewById(R.id.btnVerifyReg);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg;
                reg = regNo.getText().toString();
                if (reg.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter your registration number", Toast.LENGTH_SHORT).show();
                }else {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[1];
                            field[0] = "reg_no";


                            String[] data = new String[1];
                            data[0] = reg;

                            PutData putData = new PutData("http://10.8.122.87/project1/verifyReg.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Verification Success")){
                                        Toast.makeText(getApplicationContext(), "Verification Success", Toast.LENGTH_SHORT).show();
                                        openDocRegActivity();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Verification failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });


    }
    public void openDocRegActivity(){
        Intent intent = new Intent(this,DocUpdateDetails.class);
        String reg,name;
        reg = regNo.getText().toString();
        name = getVetName(reg);

        intent.putExtra("reg_no",reg);
        intent.putExtra("vet_name",name);
        startActivity(intent);
    }
    public String getVetName(String reg){

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "reg_no";


                String[] data = new String[1];
                data[0] = reg;

                PutData putData = new PutData("http://10.8.122.87/project1/vetName.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        result = putData.getResult().toString();

                    }
                }

            }
        });
        return result;
    }
}