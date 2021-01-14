package com.example.arouselsham.pojo.model;

public class CustomerInfoModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber ;
    private String birthDay;
    //TODO: get the gender from Strings.xml
    private final String[] gender = {"Male", "Female"};

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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String[] getGender() {
        return gender;
    }
}
