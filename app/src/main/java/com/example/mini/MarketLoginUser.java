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

public class MarketLoginUser extends AppCompatActivity {

    TextInputEditText textInputEditTextMarketUsername , textInputEditTextPassword ;
    Button buttonconlog,buttoncallog;
    TextView textViewmarketuserlog;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_login_user);

        textInputEditTextMarketUsername = findViewById(R.id.marketusernamelog);
        textInputEditTextPassword = findViewById(R.id.marketpasswordlog);
        buttonconlog = findViewById(R.id.btnconlog);
        buttoncallog = findViewById(R.id.btncal);
        textViewmarketuserlog = findViewById(R.id.txtreg);
        progressBar = findViewById(R.id.progress);

        buttoncallog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        textViewmarketuserlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarketRegisterUser.class);
                startActivity(intent);
                finish();
            }
        });

        buttonconlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marketusername,password;
                marketusername = String.valueOf(textInputEditTextMarketUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if(!marketusername.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "Marketusername";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = marketusername;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.1.38/MarketLoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Market login complete")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Home.class);
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
                    Toast.makeText(getApplicationContext(),"Please complete the information.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}