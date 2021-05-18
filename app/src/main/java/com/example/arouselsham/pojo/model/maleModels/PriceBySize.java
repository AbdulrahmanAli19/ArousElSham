package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class PriceBySize implements Serializable {
    private double mediumSizePrice;
    private double bigSizePrice;

    public PriceBySize(double mediumSizePrice, double bigSizePrice) {
        this.mediumSizePrice = mediumSizePrice;
        this.bigSizePrice = bigSizePrice;
    }

    PriceBySize() {
    }

    public Double getMediumSizePrice() {
        return mediumSizePrice;
    }

    public Double getBigSizePrice() {
        return bigSizePrice;
    }

    public void setMediumSizePrice(double mediumSizePrice) {
        this.mediumSizePrice = mediumSizePrice;
    }

    public void setBigSizePrice(double bigSizePrice) {
        this.bigSizePrice = bigSizePrice;
    }
}
