package com.example.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class UserRegister extends AppCompatActivity {

    TextInputEditText textInputEditTextUsername , textInputEditTextPassword , textInputEditTextFullname , textInputEditTextEmail , textInputEditTextTel;
    Button buttonconreg,buttoncalreg;
    TextView textViewuserlogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        textInputEditTextUsername = findViewById(R.id.usernamereg);
        textInputEditTextPassword = findViewById(R.id.passwordreg);
        textInputEditTextFullname = findViewById(R.id.fullnamereg);
        textInputEditTextEmail = findViewById(R.id.emailreg);
        textInputEditTextTel = findViewById(R.id.telreg);
        buttonconreg = findViewById(R.id.btnconreg);
        buttoncalreg = findViewById(R.id.btncal);
        progressBar = findViewById(R.id.progress);
        textViewuserlogin = findViewById(R.id.txtlog);

        buttoncalreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textViewuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
                finish();

            }
        });
        buttonconreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password,fullname,email,tel;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                fullname = String.valueOf(textInputEditTextFullname.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                tel = String.valueOf(textInputEditTextTel.getText());

                if(!username.equals("") && !password.equals("") && !fullname.equals("") && !email.equals("") && !tel.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "username";
                            field[1] = "password";
                            field[2] = "fullname";
                            field[3] = "email";
                            field[4] = "tel";
                            String[] data = new String[5];
                            data[0] = username;
                            data[1] = password;
                            data[2] = fullname;
                            data[3] = email;
                            data[4] = tel;

                            PutData putData = new PutData("http://192.168.1.38/UserLoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("User registration completed")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(UserRegister.this, "Please complete the information.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}