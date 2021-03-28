package com.example.arouselsham.pojo.model;

import java.util.ArrayList;

public class MealModel {

    private ArrayList<String> tags;
    private String arName;
    private String enName;
    private PriceByBreadTypes priceByBreadTypes;
    private PriceByOne priceByOne;
    private PriceBySize priceBySize;
    private PriceByKilogram priceByKilogram;


    public MealModel() {

    }

    /**
     * Sandwich constructor
     * */
    public MealModel(ArrayList<String> tags, String arName, String enName,
                     PriceByBreadTypes priceByBreadTypes) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByBreadTypes = priceByBreadTypes;
    }

    /**
     * constructor for the things that can be seal by peace
     * */

    public MealModel(ArrayList<String> tags, String arName, String enName, PriceByOne priceByOne) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByOne = priceByOne;
    }

    /**
     * constructor for the meals that could be sold by size
     * */

    public MealModel(ArrayList<String> tags, String arName, String enName, PriceBySize priceBySize) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceBySize = priceBySize;
    }

    /**
     * constructor for the meals that could be sold by KiloGram
     * */

    public MealModel(ArrayList<String> tags, String arName, String enName, PriceByKilogram priceByKilogram) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByKilogram = priceByKilogram;
    }

    public class Topping {
        private String toppingName;
        private double toppingNumber;

        public String getToppingName() {
            return toppingName;
        }

        public void setToppingName(String toppingName) {
            this.toppingName = toppingName;
        }

        public double getToppingNumber() {
            return toppingNumber;
        }

        public void setToppingNumber(double toppingNumber) {
            this.toppingNumber = toppingNumber;
        }
    }

    public static class PriceByBreadTypes {
        private Topping topping;
        private double syrianPrice;
        private double frenchPrice;
        private double saro5Price;

        public PriceByBreadTypes(double syrianPrice, double frenchPrice, double saro5Price) {
            this.syrianPrice = syrianPrice;
            this.frenchPrice = frenchPrice;
            this.saro5Price = saro5Price;
        }

        public double getSyrianPrice() {
            return syrianPrice;
        }

        public double getFrenchPrice() {
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

        public Topping getTopping() {
            return topping;
        }

        public void setTopping(Topping topping) {
            this.topping = topping;
        }
    }

    public static class PriceByOne {
        private Topping topping;
        private double price;

        public PriceByOne(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Topping getTopping() {
            return topping;
        }

        public void setTopping(Topping topping) {
            this.topping = topping;
        }
    }

    public static class PriceBySize {
        private double mediumSizePrice;
        private double bigSizePrice;

        public PriceBySize(double mediumSizePrice, double bigSizePrice) {
            this.mediumSizePrice = mediumSizePrice;
            this.bigSizePrice = bigSizePrice;
        }

        public double getMediumSizePrice() {
            return mediumSizePrice;
        }

        public double getBigSizePrice() {
            return bigSizePrice;
        }

        public void setMediumSizePrice(double mediumSizePrice) {
            this.mediumSizePrice = mediumSizePrice;
        }

        public void setBigSizePrice(double bigSizePrice) {
            this.bigSizePrice = bigSizePrice;
        }
    }

    public static class PriceByKilogram {
        private double kilograms;
        private double halfKilograms;
        private double quarterKilograms;
        private double tomnKilograms;

        public PriceByKilogram(double kilograms, double halfKilograms, double quarterKilograms, double tomnKilograms) {
            this.kilograms = kilograms;
            this.halfKilograms = halfKilograms;
            this.quarterKilograms = quarterKilograms;
            this.tomnKilograms = tomnKilograms;
        }

        public double getKilograms() {
            return kilograms;
        }

        public double getHalfKilograms() {
            return halfKilograms;
        }

        public double getQuarterKilograms() {
            return quarterKilograms;
        }

        public double getTomnKilograms() {
            return tomnKilograms;
        }

        public void setKilograms(double kilograms) {
            this.kilograms = kilograms;
        }

        public void setHalfKilograms(double halfKilograms) {
            this.halfKilograms = halfKilograms;
        }

        public void setQuarterKilograms(double quarterKilograms) {
            this.quarterKilograms = quarterKilograms;
        }

        public void setTomnKilograms(double tomnKilograms) {
            this.tomnKilograms = tomnKilograms;
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getArName() {
        return arName;
    }

    public String getEnName() {
        return enName;
    }

    public PriceByBreadTypes getPriceByBreadTypes() {
        return priceByBreadTypes;
    }

    public PriceByOne getPriceByOne() {
        return priceByOne;
    }

    public PriceBySize getPriceBySize() {
        return priceBySize;
    }

    public PriceByKilogram getPriceByByKilogram() {
        return priceByKilogram;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public void setPriceByBreadTypes(PriceByBreadTypes priceByBreadTypes) {
        this.priceByBreadTypes = priceByBreadTypes;
    }

    public void setPriceByOne(PriceByOne priceByOne) {
        this.priceByOne = priceByOne;
    }

    public void setPriceBySize(PriceBySize priceBySize) {
        this.priceBySize = priceBySize;
    }

    public void setPriceByByKilogram(PriceByKilogram priceByKilogram) {
        this.priceByKilogram = priceByKilogram;
    }

}