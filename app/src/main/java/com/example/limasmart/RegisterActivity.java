package com.example.limasmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button btnReg;
    EditText etUsername,etEmail,etPassword,etConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnReg = findViewById(R.id.BtnReg);
        etUsername = findViewById(R.id.username);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPass = findViewById(R.id.Confirmpassword);

        TextView alreadyRegistered = findViewById(R.id.alreadyregistered);
        alreadyRegistered.setPaintFlags(alreadyRegistered.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username, password, email, confPass;

                username = String.valueOf(etUsername.getText());
                password = String.valueOf(etPassword.getText());
                email = String.valueOf(etEmail.getText());
                confPass = String.valueOf(etConfirmPass.getText());

                //check if there is an empty field
                if (!username.isEmpty() || !password.isEmpty() || !email.isEmpty() || !confPass.isEmpty()){
                    //check if the two passwords are equal
                    if(password.equals(confPass)){
                        //validate length of password
                        if (passwordValidator(password)){
                            Handler handler = new Handler();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    StrictMode.enableDefaults();
                                    String[] field = new String[3];
                                    field[0] = "email";
                                    field[1] = "username";
                                    field[2] = "password";
                                    //Creating array for data
                                    String[] data = new String[3];
                                    data[0] = email;
                                    data[1] = username;
                                    data[2] = password;

                                    PutData putData = new PutData("http://10.8.122.87/project1/signup.php", "POST", field, data);

                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            String result = putData.getResult();
                                            if (result.equals("Sign Up Success")){
                                                //openLoginActivity();
                                                Log.i("Tag", "Success");
                                                Toast.makeText(getApplicationContext(),"Sign up success",Toast.LENGTH_LONG).show();
                                                //finish();
                                            }else if (result.equals("Sign up Failed")){
                                                Toast.makeText(getApplicationContext(),"Sign up failed",Toast.LENGTH_SHORT).show();
                                            }else if(result.equals("Error: Database connection")){
                                                Toast.makeText(getApplicationContext(),"Error: Database connection",Toast.LENGTH_SHORT).show();
                                            }else if(result.equals("All fields are required")){
                                                Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getApplicationContext(),"Not connected",Toast.LENGTH_SHORT).show();
                                            }

                                            //End ProgressBar (Set visibility to GONE)
                                            Log.i("PutData", result);
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Opposite of onComplete",Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Opposite of start put",Toast.LENGTH_SHORT).show();
                                    }
                                    //End Write and Read data with URL
                                }
                            });
                        }else{
                            String string = getString(R.string.password_validator);
                            Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "passwords not similar", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                }


            }
        });

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    public boolean passwordValidator(String password){
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void openLogin(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}