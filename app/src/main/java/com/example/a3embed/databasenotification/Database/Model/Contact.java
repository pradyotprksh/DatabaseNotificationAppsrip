package com.example.a3embed.databasenotification.Database.Model;

/**
 * Contact.java
 * Contact Model
 *
 * @version 1.0 06 Dec 2018
 * @author Pradyot Prakash
 */
public class Contact {

    private int _id;                        // Id
    private String _name;                   // Name
    private String _phone_number;           // Phone Number

    /**
     * <h>Contact()<h/>
     * <p>Contact Constructor with no parameters</p>
     */
    public Contact(){}

    /**
     * <h>Contact()<h/>
     * <p>Contact Constructor with 3 parameters</p>
     */
    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    /**
     * <h>Contact()<h/>
     * <p>Contact Constructor with 2 parameters</p>
     */
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }

    /**
     * <h>getId()<h/>
     * <p>Get the id of the contact</p>
     */
    public int getID(){
        return this._id;
    }

    /**
     * <h>setId()<h/>
     * <p>Set the id of the contact</p>
     */
    public void setID(int id){
        this._id = id;
    }

    /**
     * <h>getName()<h/>
     * <p>Get the name of the contact</p>
     */
    public String getName(){
        return this._name;
    }

    /**
     * <h>setName()<h/>
     * <p>Set the name of the contact</p>
     */
    public void setName(String name){
        this._name = name;
    }

    /**
     * <h>getPhoneNumber()<h/>
     * <p>Get the phone number of the contact</p>
     */
    public String getPhoneNumber(){
        return this._phone_number;
    }

    /**
     * <h>setPhoneNumber()<h/>
     * <p>Set the phone number of the contact</p>
     */
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

}
