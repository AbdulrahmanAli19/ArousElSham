package com.example.arouselsham.pojo.model.male;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal implements Serializable {

    private String id;
    private String arName;
    private String enName;
    private String imageUrl;
    private String arDescription;
    private String enDescription;

    private List<MenuTopping> toppings;
    private List<MenuTags> tags;
    private Map<String, Double> price = new HashMap<>();

    public Meal() {

    }

    public Meal(String arName, String enName,
                Map<String, Double> price,
                String arDescription, String enDescription,
                 List<MenuTopping> toppings, List<MenuTags> tags
    ) {
        this.arName = arName;
        this.enName = enName;
        this.arDescription = arDescription;
        this.enDescription = enDescription;
        this.toppings = toppings;
        this.tags = tags;
        this.price = price;
    }

    public Map<String, Double> getPrice() {
        return price;
    }

    public void setPrice(Map<String, Double> price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArDescription() {
        return arDescription;
    }

    public void setArDescription(String arDescription) {
        this.arDescription = arDescription;
    }

    public String getEnDescription() {
        return enDescription;
    }

    public void setEnDescription(String enDescription) {
        this.enDescription = enDescription;
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

    public List<MenuTopping> getToppings() {
        return toppings;
    }

    public void setToppings(List<MenuTopping> toppings) {
        this.toppings = toppings;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id='" + id + '\'' +
                ", arName='" + arName + '\'' +
                ", enName='" + enName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", arDescription='" + arDescription + '\'' +
                ", enDescription='" + enDescription + '\'' +
                ", toppings=" + toppings +
                ", tags=" + tags +
                ", price=" + price +
                '}';
    }
}