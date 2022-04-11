package com.example.a19520113_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.a19520113_lab2.adapter.EmployeeAdapter;
import com.example.a19520113_lab2.model.Employee;
import com.example.a19520113_lab2.model.EmployeeFulltime;
import com.example.a19520113_lab2.model.EmployeeParttime;

import java.util.ArrayList;

public class CustomAdapterForListView extends AppCompatActivity {

    private ListView lvPerson;
    private TextView tvSelection;
    private Button btnSubmit;
    private EditText etFullName;
    private EditText etID;
    private CheckBox ckbIsManager;
    private ArrayList<Employee> employees;

    private void initUi()
    {
        lvPerson = (ListView)findViewById(R.id.lv_person);
        tvSelection = (TextView)findViewById(R.id.tv_selection);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etFullName = (EditText)  findViewById(R.id.etEnterFullName);
        etID = (EditText)  findViewById(R.id.etEnterID);
        ckbIsManager = (CheckBox)  findViewById(R.id.ckb_isManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter_for_list_view);

        initUi();
        employees = new ArrayList<Employee>();
        EmployeeAdapter adapter = new EmployeeAdapter(this, android.R.layout.simple_list_item_1, employees);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Thêm dữ liệu mới vào arraylist
                addNewEmployee();
                //Cập nhật dữ liệu mới lên giao diên
                adapter.notifyDataSetChanged();
            }
        });

        //5. Xử lý sự kiện chọn một phần tử trong ListView
        lvPerson.setAdapter(adapter);
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3) {
                //đối số arg2 là vị trí phần tử trong Data Source (arr)
                tvSelection.setText("position :" + arg2 + "; value =" + employees.get(arg2));
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
        employees.add(employee);
    }
}