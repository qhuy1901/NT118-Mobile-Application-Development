package com.example.a19520113_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a19520113_lab3.adapter.ContactAdapter;
import com.example.a19520113_lab3.database.DatabaseHandler;
import com.example.a19520113_lab3.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class QuanLyContactActivity extends AppCompatActivity {
    private ListView lvContact;
    private void initUi()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_contact);
        initUi();

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact( "Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
//        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + ", Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        ContactAdapter adapter = new ContactAdapter(QuanLyContactActivity.this, android.R.layout.simple_list_item_1, contacts);
        lvContact.setAdapter(adapter);

        //6. xử lý sự kiện Long click
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                DatabaseHandler databaseHandler = new DatabaseHandler(QuanLyContactActivity.this);
                databaseHandler.deleteContact(contacts.get(pos));
                contacts.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}