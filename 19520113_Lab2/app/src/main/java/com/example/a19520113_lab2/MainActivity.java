package com.example.a19520113_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnCau1, btnCau2, btnCau3, btnCau4, btnCau5, btnCau6;

    private void initUi()
    {
        btnCau1 = (Button) findViewById(R.id.btnCau1);
        btnCau2 = (Button) findViewById(R.id.btnCau2);
        btnCau3 = (Button) findViewById(R.id.btnCau3);
        btnCau4 = (Button) findViewById(R.id.btnCau4);
        btnCau5 = (Button) findViewById(R.id.btnCau5);
        btnCau6 = (Button) findViewById(R.id.btnCau6);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(switchActivityIntent);
            }
        });

        btnCau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, ListviewActivityWithArrayList.class);
                startActivity(switchActivityIntent);
            }
        });

        btnCau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, ListViewArrayListObject.class);
                startActivity(switchActivityIntent);
            }
        });

        btnCau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, CustomAdapterForListView.class);
                startActivity(switchActivityIntent);
            }
        });

        btnCau5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, GridViewAndSpinnerActivity.class);
                startActivity(switchActivityIntent);
            }
        });

        btnCau6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}