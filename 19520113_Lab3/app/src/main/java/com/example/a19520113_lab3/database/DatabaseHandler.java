package com.example.a19520113_lab3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.a19520113_lab3.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler  extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    // Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put(KEY_ID, contact.getId());
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PH_NO, contact.getPhoneNumber());
        db.insert(TABLE_CONTACTS, null, contentValues);
    }
//    // Getting single contact
//    public Contact getContact(int id) {}
//    // Getting All Contacts
    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);

        // on below line we are creating a new array list.
        List<Contact> contacts = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contacts.add(new Contact(cursorContacts.getInt(0),
                        cursorContacts.getString(1),
                        cursorContacts.getString(2)));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contacts;
    }
//    // Updating single contact
//    public int updateContact(Contact contact) {}
//    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=" + contact.getId(), null);
        Log.e("Hihi", KEY_ID + "=" + contact.getId());
    }

}
