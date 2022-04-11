package com.example.a19520113_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a19520113_lab2.adapter.EmployeeAdapter;
import com.example.a19520113_lab2.adapter.EmployeeRecyclerViewAdapter;
import com.example.a19520113_lab2.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rcvPerson;
    private TextView tvSelection;
    private Button btnSubmit;
    private EditText etFullName;
    private EditText etID;
    private CheckBox ckbIsManager;
    private ArrayList<Employee> employees;
    private EmployeeRecyclerViewAdapter adapter;
    private EmployeeViewModel employeeViewModel;

    private void initUi()
    {
        rcvPerson = (RecyclerView) findViewById(R.id.rcv_person);
        tvSelection = (TextView)findViewById(R.id.tv_selection);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etFullName = (EditText)  findViewById(R.id.etEnterFullName);
        etID = (EditText)  findViewById(R.id.etEnterID);
        ckbIsManager = (CheckBox)  findViewById(R.id.ckb_isManager);
        rcvPerson.setLayoutManager(new LinearLayoutManager(this));
        rcvPerson.setHasFixedSize(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        employees = new ArrayList<>();
        initUi();
        adapter = new EmployeeRecyclerViewAdapter(employees, getApplicationContext());
                rcvPerson.setAdapter(adapter);
//        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
//        employeeViewModel.getEmployeeLiveData().observe(this, new Observer<List<Employee>>() {
//            @Override
//            public void onChanged(List<Employee> employees) {
//                adapter = new EmployeeRecyclerViewAdapter(employees, getApplicationContext());
//                rcvPerson.setAdapter(adapter);
//            }
//        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Thêm dữ liệu mới vào arraylist
                addNewEmployee();
                Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                //Cập nhật dữ liệu mới lên giao diên
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void addNewEmployee() {
        Employee employee = new Employee();
        boolean ckbId = ckbIsManager.isChecked();
        String id = etID.getText().toString();
        String name = etFullName.getText().toString();

        employee.setId(id);
        employee.setName(name);
        employee.setIsmanager(ckbId);
        //Đưa employee vào ArrayList
//        employeeViewModel.addNewEmployee(employee);
        employees.add(employee);
//        Toast.makeText(getApplicationContext(),"Hi " + employees.size() + employee.getName() , Toast.LENGTH_SHORT).show();
    }
}