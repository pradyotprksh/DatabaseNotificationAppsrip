package com.example.a3embed.databasenotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a3embed.databasenotification.Database.Handler.DatabaseHandler;
import com.example.a3embed.databasenotification.Database.Model.Contact;

import java.util.List;

/**
 * MainActivity.java
 * Main activity
 *
 * @version 1.0 06 Dec 2018
 * @author Pradyot Prakash
 */

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;         // Database Variable
    private TextView databaseTv;        // Output TextView
    private List<Contact> contacts;     // Contact List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText nameEt = findViewById(R.id.nameEt);              // Name EditText
        final EditText phoneEt = findViewById(R.id.phoneEt);            // Phone Number EditText
        Button addB = findViewById(R.id.addB);                          // Add Button
        Button showB = findViewById(R.id.showB);                        // Show Button
        Button subB = findViewById(R.id.subB);                          // Remove Button
        Button searchB = findViewById(R.id.searchB);                    // Search Button
        Button clearB = findViewById(R.id.clearB);                      // Clear Button
        Button countB = findViewById(R.id.countB);                      // Count Button
        Button orderByName = findViewById(R.id.orderByNameB);           // Order By Name Button
        Button orderByPhone = findViewById(R.id.orderByPhoneB);         // Order By Phone Button

        databaseTv = findViewById(R.id.databaseTv);
        db = new DatabaseHandler(this);

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nameEt.getText().toString()) && !TextUtils.isEmpty(phoneEt.getText().toString())) {
                    db.addContact(new Contact(nameEt.getText().toString(), phoneEt.getText().toString()));
                    String value = "Added";
                    databaseTv.setText(value);
                    nameEt.setText("");
                    phoneEt.setText("");
                }
            }
        });

        searchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Contact contact;
                    String value;
                    if (!TextUtils.isEmpty(nameEt.getText().toString())) {
                        contact = db.getContactName(nameEt.getText().toString());
                        value = "Found Id: " + contact.getID() + " Name: " + contact.getName() + " Phone: " + contact.getPhoneNumber() + "\n";
                        databaseTv.setText(value);
                    } else if (!TextUtils.isEmpty(phoneEt.getText().toString())) {
                        contact = db.getContactPhone(phoneEt.getText().toString());
                        value = "Found Id: " + contact.getID() + " Name: " + contact.getName() + " Phone: " + contact.getPhoneNumber() + "\n";
                        databaseTv.setText(value);
                    } else {
                        databaseTv.setText("");
                        contacts = db.getAllContacts();
                        showAllContact(contacts);
                    }
                } catch (Exception ignored) {
                    String value = "Not Found";
                    databaseTv.setText(value);
                }
            }
        });

        subB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Contact contact;
                    String value;
                    if (!TextUtils.isEmpty(nameEt.getText().toString())) {
                        contact = db.deleteContactByName(nameEt.getText().toString());
                        value = "Found and deleted Id: " + contact.getID() + " Name: " + contact.getName() + " Phone: " + contact.getPhoneNumber() + "\n";
                        databaseTv.setText(value);
                    } else if (!TextUtils.isEmpty(phoneEt.getText().toString())) {
                        contact = db.deleteContactByPhone(phoneEt.getText().toString());
                        value = "Found and deleted Id: " + contact.getID() + " Name: " + contact.getName() + " Phone: " + contact.getPhoneNumber() + "\n";
                        databaseTv.setText(value);
                    } else {
                        databaseTv.setText("");
                        contacts = db.getAllContacts();
                        showAllContact(contacts);
                    }
                } catch (Exception ignored) {
                    String value = "Not Found";
                    databaseTv.setText(value);
                }
            }
        });

        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.clearDatabase();
                databaseTv.setText("");
                contacts = db.getAllContacts();
                showAllContact(contacts);
            }
        });

        countB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseTv.setText(String.valueOf(db.getContactsCount()));
            }
        });

        orderByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseTv.setText("Order by Name \n");
                contacts = db.getAllContactsOrderByName();
                showAllContact(contacts);
            }
        });

        orderByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseTv.setText("Order By Phone Number: \n");
                contacts = db.getAllContactsOrderByPhoneNumber();
                showAllContact(contacts);
            }
        });

        showB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseTv.setText("");
                contacts = db.getAllContacts();
                showAllContact(contacts);
            }
        });

    }

    /**
     * <h>showAllContact()<h/>
     * <p>Show All The Contacts</p>
     */
    void showAllContact(List<Contact> contacts) {
        for (Contact cn : contacts) {
            String value = "Id: " + cn.getID() + " Name: " + cn.getName() + " Phone: " +
                    cn.getPhoneNumber();
            databaseTv.append(value + "\n");
        }
    }

}
