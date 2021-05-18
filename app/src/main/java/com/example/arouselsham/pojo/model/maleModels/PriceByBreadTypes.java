package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class PriceByBreadTypes implements Serializable {

    private double syrianPrice;
    private double frenchPrice;
    private double saro5Price;

    PriceByBreadTypes() {
    }

    public PriceByBreadTypes(double syrianPrice, double frenchPrice, double saro5Price) {
        this.syrianPrice = syrianPrice;
        this.frenchPrice = frenchPrice;
        this.saro5Price = saro5Price;
    }

    public double getSyrianPrice() {
        return syrianPrice;
    }

    public Double getFrenchPrice() {
        return frenchPrice;
    }

    public double getSaro5Price() {
        return saro5Price;
    }

    public void setSyrianPrice(double syrianPrice) {
        this.syrianPrice = syrianPrice;
    }

    public void setFrenchPrice(double frenchPrice) {
        this.frenchPrice = frenchPrice;
    }

    public void setSaro5Price(double saro5Price) {
        this.saro5Price = saro5Price;
    }
}
