package com.example.a19520113_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnBai1, btnBai2, btnBai4;
    private void initUi()
    {
        btnBai4 = (Button) findViewById(R.id.btnBai4);
        btnBai2 = (Button) findViewById(R.id.btnBai2);
        btnBai1 = (Button) findViewById(R.id.btnBai1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        btnBai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Bai1Activity.class);
                startActivity(i);
            }
        });
        btnBai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Bai2Activity.class);
                startActivity(i);
            }
        });
        btnBai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Bai4Activity.class);
                startActivity(i);
            }
        });
    }
}