package com.example.arouselsham.pojo.model.maleModels;

public class SelectorModel {
   private String tag;
   private Double price ;

    public SelectorModel(String tag, Double price) {
        this.tag = tag;
        this.price = price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
