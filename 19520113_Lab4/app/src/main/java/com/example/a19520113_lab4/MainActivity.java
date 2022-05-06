package com.example.a19520113_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCau1, btnCau2, btnCau3;
    private void initUi()
    {
        btnCau2 = (Button) findViewById(R.id.btn_cau2);
        btnCau1 = (Button) findViewById(R.id.btn_cau1);
        btnCau3 = (Button) findViewById(R.id.btn_cau3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, Bai1Activity.class);
                startActivity(switchActivityIntent);
            }
        });
        btnCau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, Bai2Activity.class);
                startActivity(switchActivityIntent);
            }
        });
        btnCau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, Bai3Activity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}