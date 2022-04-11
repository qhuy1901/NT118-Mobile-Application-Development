package com.example.a19520113_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnThamKhao, btnQuanLyContact, btnQuanLySinhVien;

    private void initUi()
    {
        btnThamKhao = (Button) findViewById(R.id.btn_tham_khao);
        btnQuanLyContact = (Button) findViewById(R.id.btn_ql_contact);
        btnQuanLySinhVien = (Button) findViewById(R.id.btn_ql_sinhvien);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        btnThamKhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, ThamKhaoActivity.class);
                startActivity(switchActivityIntent);
            }
        });

        btnQuanLyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, QuanLyContactActivity.class);
                startActivity(switchActivityIntent);
            }
        });

        btnQuanLySinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(MainActivity.this, DanhSachSinhVienActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}