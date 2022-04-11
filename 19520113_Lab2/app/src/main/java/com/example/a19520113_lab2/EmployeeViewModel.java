package com.example.a19520113_lab2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a19520113_lab2.adapter.EmployeeRecyclerViewAdapter;
import com.example.a19520113_lab2.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    private MutableLiveData<List<Employee>>employeeLiveData;
    private List<Employee> employeeList;

    public EmployeeViewModel(){
        employeeLiveData = new MutableLiveData<>();
        employeeList = new ArrayList<>();
    }

    public MutableLiveData<List<Employee>> getEmployeeLiveData()
    {
        return employeeLiveData;
    }

    public void addNewEmployee(Employee employee)
    {
        employeeList.add(employee);
        employeeLiveData.setValue(employeeList);
    }
}
