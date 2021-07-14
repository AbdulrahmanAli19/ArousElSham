package com.example.arouselsham.pojo.model.maleModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MenuSection implements Serializable {

    private Double menuVersion;

    @SerializedName("tagsMap")
    private List<Map<String, String>> mealsList;

    public MenuSection() {
    }

    public MenuSection(Double menuVersion, List<Map<String, String>> mealsList) {
        this.menuVersion = menuVersion;
        this.mealsList = mealsList;
    }

    public Double getMenuVersion() {
        return menuVersion;
    }

    public void setMenuVersion(Double menuVersion) {
        this.menuVersion = menuVersion;
    }

    public List<Map<String, String>> getMealsList() {
        return mealsList;
    }

    public void setMealsList(List<Map<String, String>> mealsList) {
        this.mealsList = mealsList;
    }
}
