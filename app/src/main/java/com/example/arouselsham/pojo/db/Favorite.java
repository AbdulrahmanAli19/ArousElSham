package com.example.arouselsham.pojo.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.maleModels.Meal;

@Entity(tableName = "favorite_table")
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Meal meal;

    public Favorite() {
    }

    public Favorite(Meal meal) {
        this.meal = meal;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
