package com.example.arouselsham.pojo.model.maleModels;

public class MenuTopping {
    private String toppingArName;
    private String toppingEnName;
    private double toppingPrice;

    public MenuTopping(String toppingArName, String toppingEnName, double toppingPrice) {
        this.toppingArName = toppingArName;
        this.toppingEnName = toppingEnName;
        this.toppingPrice = toppingPrice;
    }

    public MenuTopping() {
    }

    public String getToppingArName() {
        return toppingArName;
    }

    public void setToppingArName(String toppingArName) {
        this.toppingArName = toppingArName;
    }

    public String getToppingEnName() {
        return toppingEnName;
    }

    public void setToppingEnName(String toppingEnName) {
        this.toppingEnName = toppingEnName;
    }

    public Double getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(double toppingPrice) {
        this.toppingPrice = toppingPrice;
    }
}
