package com.example.a19520113_lab2.model;

public class EmployeeParttime extends Employee
{
    @Override
    public double tinhLuong() {return 150;}
    @Override
    public String toString() {return super.toString()+ "--> PartTime = "+ tinhLuong();}
}

