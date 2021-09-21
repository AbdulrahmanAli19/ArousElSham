package com.example.arouselsham.pojo.model;

import java.io.Serializable;

public class UserAddress implements Serializable {

    private String area;
    private String streetName;
    private String landMark;
    private String buildingType;
    private String buildingNumber;
    private String floorNumber;
    private String aptNumber;
    private String landLineNumber;
    private String phoneNumber;

    public UserAddress() {
    }

    public UserAddress(String area, String streetName, String landMark, String buildingType,
                       String buildingNumber, String phoneNumber) {
        this.area = area;
        this.buildingNumber = buildingNumber;
        this.streetName = streetName;
        this.landMark = landMark;
        this.buildingType = buildingType;
        this.phoneNumber = phoneNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getLandLineNumber() {
        return landLineNumber;
    }

    public void setLandLineNumber(String landLineNumber) {
        this.landLineNumber = landLineNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
