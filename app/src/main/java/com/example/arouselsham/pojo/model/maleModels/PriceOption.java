package com.example.arouselsham.pojo.model.maleModels;

import com.example.arouselsham.pojo.Common;

public class PriceOption {
   private String option;
   private Double price ;
   private int priority;

    public PriceOption() {
    }

    public PriceOption(String option, Double price) {
        this.option = option;
        this.price = price;
        if (option.equals(Common.ARABIC_BREAD)){
            priority = 1 ;
        }else if (option.equals(Common.FRENCH_BREAD)){
            priority = 2;
        }else if (option.equals(Common.SARO_5_BREAD)){
            priority = 3;
        }else if (option.equals(Common.MEDIUM_PRICE)){
            priority = 4;
        }else if (option.equals(Common.LARGE)){
            priority = 5;
        }else if (option.equals(Common.TOMN_KILOGRAMS)){
            priority = 6;
        }else if (option.equals(Common.QUARTER_KILOGRAMS)){
            priority = 7;
        }else if (option.equals(Common.HALF_KILOGRAMS)){
            priority = 8;
        }else if (option.equals(Common.KILOGRAM)){
            priority = 9;
        }else if (option.equals(Common.QUARTER_CHICKEN)){
            priority = 10;
        }else if (option.equals(Common.HALF_CHICKEN)){
            priority = 11;
        }else if (option.equals(Common.CHICKEN)){
            priority = 12;
        }
    }

    public int getPriority() {
        return priority;
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
