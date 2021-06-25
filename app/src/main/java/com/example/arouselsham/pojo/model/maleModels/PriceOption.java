package com.example.arouselsham.pojo.model.maleModels;

public class PriceOption {
   private String option;
   private Double price ;

    public PriceOption(String option, Double price) {
        this.option = option;
        this.price = price;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
