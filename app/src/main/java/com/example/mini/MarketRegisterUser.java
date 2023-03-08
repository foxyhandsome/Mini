package com.example.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MarketRegisterUser extends AppCompatActivity {

    TextInputEditText textInputEditTextMarketUsername , textInputEditTextPassword ,  textInputEditTextEmail , textInputEditTextTel;
    Button buttonconreg,buttoncalreg;
    TextView textViewmarketuserlogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_register_user);

        textInputEditTextMarketUsername = findViewById(R.id.marketusernamereg);
        textInputEditTextPassword = findViewById(R.id.marketpasswordreg);
        textInputEditTextEmail = findViewById(R.id.emailreg);
        textInputEditTextTel = findViewById(R.id.telreg);
        buttonconreg = findViewById(R.id.btnconreg);
        buttoncalreg = findViewById(R.id.btncal);
        progressBar = findViewById(R.id.progress);
        textViewmarketuserlogin = findViewById(R.id.txtlog);

        buttoncalreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textViewmarketuserlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarketLoginUser.class);
                startActivity(intent);
                finish();

            }
        });
        buttonconreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marketusername,password,email,tel;
                marketusername = String.valueOf(textInputEditTextMarketUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                tel = String.valueOf(textInputEditTextTel.getText());

                if(!marketusername.equals("") && !password.equals("") && !email.equals("") && !tel.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[4];
                            field[0] = "Marketusername";
                            field[1] = "password";
                            field[2] = "email";
                            field[3] = "tel";
                            String[] data = new String[4];
                            data[0] = marketusername;
                            data[1] = password;
                            data[2] = email;
                            data[3] = tel;

                            PutData putData = new PutData("http://192.168.1.38/MarketLoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Market registration completed")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MarketDetail.class);
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
                    Toast.makeText(MarketRegisterUser.this, "Please complete the information.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}