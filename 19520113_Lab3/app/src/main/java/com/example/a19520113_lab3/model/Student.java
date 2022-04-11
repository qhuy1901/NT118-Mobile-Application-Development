package com.example.a19520113_lab3.model;

import java.io.Serializable;

public class Student implements Serializable
{
    private int studentId;
    private String fullName;
    private int gender;
    private String dob;
    private String className;
    private double gpa;

    public Student() {
    }

    public Student(int studentId, String fullName, String dob,  int gender, String className, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.className = className;
        this.gpa = gpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
