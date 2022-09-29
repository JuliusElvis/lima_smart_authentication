package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText etUsername,etPassword;
    TextView reg_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn= findViewById(R.id.loginButton);
        etUsername=findViewById(R.id.usernameEt);
        etPassword = findViewById(R.id.passwordEt);
        reg_activity = findViewById(R.id.text1);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username,password;
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (username.length()>=3 && password.length()>=8){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";

                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://10.8.122.87/project1/login.php", "POST", field, data);

                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")) {
                                        Toast.makeText(getApplicationContext(), "Log in success", Toast.LENGTH_SHORT).show();
                                        openHomeActivity();
                                        //finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Log in failed", Toast.LENGTH_SHORT).show();
                                    }

                                    //End ProgressBar (Set visibility to GONE)
                                    Log.i("PutData", result);
                                }
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"username or password must be more than 8 characters long",Toast.LENGTH_SHORT).show();
                }
            }
        });
        reg_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegActivity();
            }
        });
    }

    public void openRegActivity(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}