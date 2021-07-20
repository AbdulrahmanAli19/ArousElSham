package com.example.arouselsham.pojo.model.maleModels;


import java.io.Serializable;
import java.util.List;

public class ListOfMeals implements Serializable {
    private List<Meal> meals;

    public ListOfMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public ListOfMeals() {
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
