package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class PriceByOne implements Serializable {

    private Double price;

    public PriceByOne() {
    }

    public PriceByOne(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
