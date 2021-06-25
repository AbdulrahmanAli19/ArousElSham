package com.example.arouselsham.pojo.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.maleModels.Meal;

@Entity(tableName = "menu_table")
public class Menu {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Meal meal;

    public Menu() {
    }

    public Menu(Meal meal) {
        this.meal = meal;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
