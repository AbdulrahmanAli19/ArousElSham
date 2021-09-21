package com.example.arouselsham.pojo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long birthDay;
    private Long creationDate;
    private String gender;
    private boolean isP1Granted;
    private boolean isP2Granted;

    private ArrayList<UserAddress> addresses = new ArrayList<>();



    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Long birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String meal) {
        gender = meal;
    }

    public boolean isP1Granted() {
        return isP1Granted;
    }

    public void setP1Granted(boolean p1Granted) {
        isP1Granted = p1Granted;
    }

    public boolean isP2Granted() {
        return isP2Granted;
    }

    public void setP2Granted(boolean p2Granted) {
        isP2Granted = p2Granted;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<UserAddress> addresses) {
        this.addresses = addresses;
    }
}
