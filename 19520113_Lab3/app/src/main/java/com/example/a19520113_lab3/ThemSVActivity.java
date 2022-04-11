package com.example.a19520113_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a19520113_lab3.database.StudentDatabase;
import com.example.a19520113_lab3.model.Student;

public class ThemSVActivity extends AppCompatActivity {
    private EditText etFullName, etGPA, etDOB;
    private RadioButton rbMale, rbFemale;
    private Spinner spnClassName;
    private Button btnInsert;

    private void initUi()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etFullName = (EditText) findViewById(R.id.activity_them_sv_et_full_name);
        etGPA = (EditText) findViewById(R.id.activity_them_sv_et_gpa);
        etDOB = (EditText) findViewById(R.id.activity_them_sv_et_dob);
        rbMale = (RadioButton) findViewById(R.id.activity_them_sv_rb_male);
        rbFemale = (RadioButton) findViewById(R.id.activity_them_sv_rb_female);
        spnClassName = (Spinner) findViewById(R.id.activity_them_sv_spn_class_name);
        btnInsert = (Button) findViewById(R.id.activity_them_sv_btn_them_sv);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ThemSVActivity.this, R.array.class_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClassName.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_svactivity);
        initUi();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setFullName(etFullName.getText().toString());
                student.setClassName(spnClassName.getSelectedItem().toString());
                student.setDob(etDOB.getText().toString());
                student.setGpa(Double.parseDouble(etGPA.getText().toString()));
                if(rbMale.isChecked())
                    student.setGender(1);
                else
                    student.setGender(0);

                StudentDatabase studentDatabase = new StudentDatabase(ThemSVActivity.this);
                studentDatabase.insertStudent(student);

                Toast.makeText(ThemSVActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                Intent switchActivityIntent = new Intent(ThemSVActivity.this, DanhSachSinhVienActivity.class);
                startActivity(switchActivityIntent);
            }
        });


    }
}