package com.example.a19520113_lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a19520113_lab3.adapter.StudentAdapter;
import com.example.a19520113_lab3.database.DatabaseHandler;
import com.example.a19520113_lab3.database.StudentDatabase;
import com.example.a19520113_lab3.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DanhSachSinhVienActivity extends AppCompatActivity {
    private FloatingActionButton btnAddStudent;
    private RecyclerView rcvStudent;
    private List<Student> students;
    private StudentAdapter studentAdapter;
    private StudentDatabase db;

    private void initUi()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAddStudent = (FloatingActionButton) findViewById(R.id.activity_dssv_btn_add_student);
        rcvStudent = (RecyclerView) findViewById(R.id.activity_dssv_rcv_student);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachSinhVienActivity.this);
        rcvStudent.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(DanhSachSinhVienActivity.this,DividerItemDecoration.VERTICAL);
        rcvStudent.addItemDecoration(dividerItemDecoration);

        db = new StudentDatabase(this);

        students = db.getAllStudents();
        studentAdapter = new StudentAdapter(students, DanhSachSinhVienActivity.this);
        rcvStudent.setAdapter(studentAdapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien);
        initUi();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(DanhSachSinhVienActivity.this, ThemSVActivity.class);
                startActivity(switchActivityIntent);
            }
        });

    }
}