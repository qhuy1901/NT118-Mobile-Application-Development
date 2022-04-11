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
import android.widget.TextView;
import android.widget.Toast;

import com.example.a19520113_lab3.database.StudentDatabase;
import com.example.a19520113_lab3.model.Student;

import java.util.Arrays;
import java.util.List;

public class UpdateStudentActivity extends AppCompatActivity {
    private EditText etFullName, etGPA, etDOB;
    private RadioButton rbMale, rbFemale;
    private Spinner spnClassName;
    private Button btnUpdate;
    private TextView tvTitle;
    private Student student;

    private void initUi()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etFullName = (EditText) findViewById(R.id.activity_update_sv_et_full_name);
        etGPA = (EditText) findViewById(R.id.activity_update_sv_et_gpa);
        etDOB = (EditText) findViewById(R.id.activity_update_sv_et_dob);
        rbMale = (RadioButton) findViewById(R.id.activity_update_sv_rb_male);
        rbFemale = (RadioButton) findViewById(R.id.activity_update_sv_rb_female);
        spnClassName = (Spinner) findViewById(R.id.activity_update_sv_spn_class_name);
        btnUpdate = (Button) findViewById(R.id.activity_update_sv_btn_update_sv);
        tvTitle = (TextView) findViewById(R.id.activity_update_sv_tv_title) ;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UpdateStudentActivity.this, R.array.class_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClassName.setAdapter(adapter);
    }

    private void loadStudentInfo()
    {
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("studentInfo");

        StudentDatabase studentDatabase = new StudentDatabase(this);
        etFullName.setText(student.getFullName());
        etGPA.setText(student.getGpa()+"");
        etDOB.setText(student.getDob());
        if(student.getGender() == 1)
            rbMale.setChecked(true);
        else
            rbFemale.setChecked(true);

        List<String> classNameList = Arrays.asList(getResources().getStringArray(R.array.class_name));
        for(int i = 0; i < classNameList.size(); i++)
        {
            if(classNameList.get(i).equals(student.getClassName()))
            {
                spnClassName.setSelection(i);
                break;
            }
        }
        tvTitle.setText("Thông tin sinh viên #" + student.getStudentId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        initUi();
        loadStudentInfo();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDatabase studentDatabase = new StudentDatabase(UpdateStudentActivity.this);
                Student newInfo = new Student();
                newInfo.setStudentId(student.getStudentId());
                newInfo.setFullName(etFullName.getText().toString());
                newInfo.setClassName(spnClassName.getSelectedItem().toString());
                newInfo.setDob(etDOB.getText().toString());
                newInfo.setGpa(Double.parseDouble(etGPA.getText().toString()));
                if(rbMale.isChecked())
                    newInfo.setGender(1);
                else
                    newInfo.setGender(0);
                studentDatabase.updateStudent(newInfo);

                Toast.makeText(UpdateStudentActivity.this, "Cập nhật sinh viên thành công!", Toast.LENGTH_SHORT).show();
                Intent switchActivityIntent = new Intent(UpdateStudentActivity.this, DanhSachSinhVienActivity.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}