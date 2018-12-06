package com.example.a3embed.databasenotification.Database.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a3embed.databasenotification.Database.Model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHandler.java
 * Database handler methods
 *
 * @version 1.0 06 Dec 2018
 * @author Pradyot Prakash
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;                          // Database Version
    private static final String DATABASE_NAME = "contactsManager";          // Database Name
    private static final String TABLE_CONTACTS = "contacts";                // Table Name
    private static final String KEY_ID = "id";                              // Column ID
    private static final String KEY_NAME = "name";                          // Column Name
    private static final String KEY_PH_NO = "phone_number";                 // Column Phone

    /**
     * <h>DatabaseHandler()<h/>
     * <p>Database Handler Constructor</p>
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    /**
     * <h>addContact()<h/>
     * <p>Add contact to database</p>
     */
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    /**
     * <h>getContactName()<h/>
     * <p>Get the contact with the name value</p>
     */
    public Contact getContactName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_NAME + "=?",
                new String[] { name }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = null;
        if (cursor != null) {
            contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
        }

        db.close();
        if (cursor != null) {
            cursor.close();
        }

        return contact;
    }

    /**
     * <h>getContactPhone()<h/>
     * <p>Get the contact with the phone value</p>
     */
    public Contact getContactPhone(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, KEY_PH_NO + "=?",
                new String[] { phone }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = null;
        if (cursor != null) {
            contact = new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
        }

        db.close();
        if (cursor != null) {
            cursor.close();
        }

        return contact;
    }

    /**
     * <h>getAllContacts()<h/>
     * <p>Get all contacts</p>
     */
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return contactList;
    }

    /**
     * <h>deleteContactName()<h/>
     * <p>Delete the contact with the name value</p>
     */
    public Contact deleteContactByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Contact contact = getContactName(name);

        db.delete(TABLE_CONTACTS, KEY_NAME + " = ?",
                new String[] { name });

        db.close();

        return contact;
    }

    /**
     * <h>deleteContactPhone()<h/>
     * <p>Delete the contact with the phone value</p>
     */
    public Contact deleteContactByPhone(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        Contact contact = getContactPhone(phone);

        db.delete(TABLE_CONTACTS, KEY_PH_NO + " = ?",
                new String[] { phone });

        db.close();

        return contact;
    }

    /**
     * <h>clearDatabase()<h/>
     * <p>Delete the all contact</p>
     */
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CONTACTS);
    }

    /**
     * <h>getContactsCount()<h/>
     * <p>Get contact count in the database</p>
     */
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    /**
     * <h>getAllContactsOrderByName()<h/>
     * <p>Get all the contact order by name</p>
     */
    public List<Contact> getAllContactsOrderByName() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, null,
                null, null, null, KEY_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return contactList;
    }

    /**
     * <h>getAllContactsOrderByPhoneNumber()<h/>
     * <p>Get all the contact order by phone number</p>
     */
    public List<Contact> getAllContactsOrderByPhoneNumber() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, null,
                null, null, null, KEY_PH_NO, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return contactList;
    }

}