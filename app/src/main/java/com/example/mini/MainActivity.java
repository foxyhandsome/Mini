package com.example.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonloginmember=findViewById(R.id.loguser);
        Button buttonloginmarket=findViewById(R.id.logmarket);
        Button buttonloginadmin=findViewById(R.id.logadmin);

        buttonloginmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserLogin.class);
                startActivity(intent);
                finish();
            }
        });

        buttonloginmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MarketLoginUser.class);
                startActivity(intent);
                finish();
            }
        });

        buttonloginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}