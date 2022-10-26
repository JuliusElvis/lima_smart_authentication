package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class DocUpdateDetails extends AppCompatActivity {
    String name,reg_no;
    TextView vetName;
    Button button;
    EditText etlocality,etphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_update_details);

        name = getIntent().getExtras().getString("vet_name");

        reg_no = getIntent().getExtras().getString("reg_no");
        vetName = findViewById(R.id.vetnametv);
        vetName.setText(name);
        button = findViewById(R.id.buttonUpdateDetails);
        etlocality = findViewById(R.id.etLocality);
        etphone = findViewById(R.id.etPhone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locality,phone;
                locality = etlocality.getText().toString();
                phone = etphone.getText().toString();

                if (locality.length()>=3 && phone.length()>=8){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[3];
                            field[0] = "phone";
                            field[1] = "locality";
                            field[2] = "reg_no";

                            String[] data = new String[3];
                            data[0] = phone;
                            data[1] = locality;
                            data[2] = reg_no;

                            PutData putData = new PutData("http://10.8.122.87/project1/vetDetails.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Update Success")) {
                                        Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_SHORT).show();
                                        openHomeActivity();
                                    }
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "Enter a valid phone number and locality", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}