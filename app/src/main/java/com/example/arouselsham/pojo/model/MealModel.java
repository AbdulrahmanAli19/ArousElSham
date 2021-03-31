package com.example.arouselsham.pojo.model;

import java.util.List;

public class MealModel {

    private List<Tags> tags;
    private String arName;
    private String enName;
    private PriceByBreadTypes priceByBreadTypes;
    private PriceByOne priceByOne;
    private PriceBySize priceBySize;
    private PriceByKilogram priceByKilogram;
    private List<Topping> toppings;


    public MealModel() {

    }

    /**
     * Sandwich constructor
     */
    public MealModel(List<Tags> tags, String arName, String enName,
                     PriceByBreadTypes priceByBreadTypes, List<Topping> toppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByBreadTypes = priceByBreadTypes;
        this.toppings = toppings;
    }

    /**
     * constructor for the things that can be seal by peace
     */

    public MealModel(List<Tags> tags, String arName, String enName, PriceByOne priceByOne, List<Topping> toppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByOne = priceByOne;
        this.toppings = toppings;
    }

    /**
     * constructor for the meals that could be sold by size
     */

    public MealModel(List<Tags> tags, String arName, String enName, PriceBySize priceBySize, List<Topping> toppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceBySize = priceBySize;
        this.toppings = toppings;
    }

    /**
     * constructor for the meals that could be sold by KiloGram
     */

    public MealModel(List<Tags> tags, String arName, String enName, PriceByKilogram priceByKilogram, List<Topping> toppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByKilogram = priceByKilogram;
        this.toppings = toppings;
    }

    public static class Topping {
        private String toppingArName;
        private String toppingEnName;
        private Double toppingPrice;

        public Topping(String toppingArName, String toppingEnName, Double toppingPrice) {
            this.toppingArName = toppingArName;
            this.toppingEnName = toppingEnName;
            this.toppingPrice = toppingPrice;
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

        public void setToppingPrice(Double toppingPrice) {
            this.toppingPrice = toppingPrice;
        }
    }

    public static class PriceByBreadTypes {

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

    }

    public static class PriceByOne {
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

    public static class Tags {
        private String enName;
        private String arName;

        public Tags(String enName, String arName) {
            this.enName = enName;
            this.arName = arName;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getArName() {
            return arName;
        }

        public void setArName(String arName) {
            this.arName = arName;
        }
    }

    public static class PriceByKilogram {
        private Double kilograms;
        private Double halfKilograms;
        private Double quarterKilograms;
        private Double tomnKilograms;

        public PriceByKilogram(Double kilograms, Double halfKilograms, Double quarterKilograms, Double tomnKilograms) {
            this.kilograms = kilograms;
            this.halfKilograms = halfKilograms;
            this.quarterKilograms = quarterKilograms;
            this.tomnKilograms = tomnKilograms;
        }

        public Double getKilograms() {
            return kilograms;
        }

        public void setKilograms(Double kilograms) {
            this.kilograms = kilograms;
        }

        public Double getHalfKilograms() {
            return halfKilograms;
        }

        public void setHalfKilograms(Double halfKilograms) {
            this.halfKilograms = halfKilograms;
        }

        public Double getQuarterKilograms() {
            return quarterKilograms;
        }

        public void setQuarterKilograms(Double quarterKilograms) {
            this.quarterKilograms = quarterKilograms;
        }

        public Double getTomnKilograms() {
            return tomnKilograms;
        }

        public void setTomnKilograms(Double tomnKilograms) {
            this.tomnKilograms = tomnKilograms;
        }
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


    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public PriceByKilogram getPriceByKilogram() {
        return priceByKilogram;
    }

    public void setPriceByKilogram(PriceByKilogram priceByKilogram) {
        this.priceByKilogram = priceByKilogram;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}