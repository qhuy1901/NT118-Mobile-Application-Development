package com.example.a19520113_lab3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.a19520113_lab3.model.Contact;
import com.example.a19520113_lab3.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabase  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "studentManagement";
    // Contacts table name
    private static final String TABLE_CONTACTS = "student";
    // Contacts Table Columns names
    private static final String KEY_ID = "studentId";
    private static final String KEY_NAME = "fullName";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_CLASS_NAME = "className";
    private static final String KEY_GPA = "gpa";

    public StudentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_DOB + " TEXT," + KEY_GENDER + " INTEGER," + KEY_CLASS_NAME + " TEXT," + KEY_GPA + " REAL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Adding
    public void insertStudent(Student student) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, student.getFullName());
        contentValues.put(KEY_CLASS_NAME, student.getClassName());
        contentValues.put(KEY_DOB, student.getDob());
        contentValues.put(KEY_GENDER, student.getGender());
        contentValues.put(KEY_GPA, student.getGpa());
        db.insert(TABLE_CONTACTS, null, contentValues);
    }

    // Getting All Student
    public List<Student> getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        List<Student> students = new ArrayList<>();
        if (cursorContacts.moveToFirst()) {
            do {
                students.add(new Student(cursorContacts.getInt(0),
                        cursorContacts.getString(1),
                        cursorContacts.getString(2),
                        cursorContacts.getInt(3),
                        cursorContacts.getString(4),
                        cursorContacts.getDouble(5)));
            } while (cursorContacts.moveToNext());
        }
        cursorContacts.close();
        return students;
    }

    public Student getStudent(String studentId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " WHERE studentId =" + studentId, null);
        Student student = new Student();
        if (cursorContacts.moveToFirst()) {
            student.setStudentId(cursorContacts.getInt(0));
            student.setFullName(cursorContacts.getString(1));
            student.setDob(cursorContacts.getString(2));
            student.setGender( cursorContacts.getInt(3));
            student.setGpa(cursorContacts.getDouble(5));
            student.setClassName( cursorContacts.getString(4));
        }
        cursorContacts.close();
        return student;
    }

    // Updating single student
    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, student.getFullName());
        contentValues.put(KEY_CLASS_NAME, student.getClassName());
        contentValues.put(KEY_DOB, student.getDob());
        contentValues.put(KEY_GENDER, student.getGender());
        contentValues.put(KEY_GPA, student.getGpa());

        db.update(TABLE_CONTACTS,  contentValues, "studentId = " + student.getStudentId(),null);
    }

    // Deleting single student
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=" + student.getStudentId(), null);
    }
}
