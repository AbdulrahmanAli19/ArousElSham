package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;
import java.util.List;

public class MealModel implements Serializable {
    private String arName;
    private String enName;
    private PriceByPiece priceByPiece;
    private PriceByBreadTypes priceByBreadTypes;
    private PriceByOne priceByOne;
    private PriceBySize priceBySize;
    private PriceByKilogram priceByKilogram;
    private List<MenuTopping> menuToppings;
    private List<MenuTags> tags;
    private boolean isExpanded = false;

     MealModel() {

    }

     MealModel(List<MenuTags> tags, String arName, String enName, PriceByPiece priceByPiece,
               PriceByBreadTypes priceByBreadTypes, PriceByOne priceByOne,
               PriceBySize priceBySize, PriceByKilogram priceByKilogram,
               List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByPiece = priceByPiece;
        this.priceByBreadTypes = priceByBreadTypes;
        this.priceByOne = priceByOne;
        this.priceBySize = priceBySize;
        this.priceByKilogram = priceByKilogram;
        this.menuToppings = menuToppings;
    }

    /**
     * Sandwich constructor
     */

     MealModel(List<MenuTags> tags, String arName, String enName,
               PriceByBreadTypes priceByBreadTypes, List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByBreadTypes = priceByBreadTypes;
        this.menuToppings = menuToppings;
    }

    /**
     * Sandwich constructor
     */

     MealModel(List<MenuTags> tags, String arName, String enName,
               PriceByPiece priceByPiece, List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByPiece = priceByPiece;
        this.menuToppings = menuToppings;
    }

    /**
     * constructor for the things that can be seal by peace
     */

     MealModel(List<MenuTags> tags, String arName, String enName, PriceByOne priceByOne, List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByOne = priceByOne;
        this.menuToppings = menuToppings;
    }

    /**
     * constructor for the meals that could be sold by size
     */

     MealModel(List<MenuTags> tags, String arName, String enName, PriceBySize priceBySize, List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceBySize = priceBySize;
        this.menuToppings = menuToppings;
    }

    /**
     * constructor for the meals that could be sold by KiloGram
     */

     MealModel(List<MenuTags> tags, String arName, String enName, PriceByKilogram priceByKilogram, List<MenuTopping> menuToppings) {
        this.tags = tags;
        this.arName = arName;
        this.enName = enName;
        this.priceByKilogram = priceByKilogram;
        this.menuToppings = menuToppings;
    }

    public List<MenuTags> getTags() {
        return tags;
    }

    public void setTags(List<MenuTags> tags) {
        this.tags = tags;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public PriceByPiece getPriceByPiece() {
        return priceByPiece;
    }

    public void setPriceByPiece(PriceByPiece priceByPiece) {
        this.priceByPiece = priceByPiece;
    }

    public PriceByBreadTypes getPriceByBreadTypes() {
        return priceByBreadTypes;
    }

    public void setPriceByBreadTypes(PriceByBreadTypes priceByBreadTypes) {
        this.priceByBreadTypes = priceByBreadTypes;
    }

    public PriceByOne getPriceByOne() {
        return priceByOne;
    }

    public void setPriceByOne(PriceByOne priceByOne) {
        this.priceByOne = priceByOne;
    }

    public PriceBySize getPriceBySize() {
        return priceBySize;
    }

    public void setPriceBySize(PriceBySize priceBySize) {
        this.priceBySize = priceBySize;
    }

    public PriceByKilogram getPriceByKilogram() {
        return priceByKilogram;
    }

    public void setPriceByKilogram(PriceByKilogram priceByKilogram) {
        this.priceByKilogram = priceByKilogram;
    }

    public List<MenuTopping> getMenuToppings() {
        return menuToppings;
    }

    public void setMenuToppings(List<MenuTopping> menuToppings) {
        this.menuToppings = menuToppings;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public String toString() {
        return "MealModel{" +
                "tags=" + tags +
                ", arName='" + arName + '\'' +
                ", enName='" + enName + '\'' +
                ", priceByPiece=" + priceByPiece +
                ", priceByBreadTypes=" + priceByBreadTypes +
                ", priceByOne=" + priceByOne +
                ", priceBySize=" + priceBySize +
                ", priceByKilogram=" + priceByKilogram +
                ", menuToppings=" + menuToppings +
                '}';
    }
}