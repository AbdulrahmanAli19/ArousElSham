package com.example.arouselsham.pojo.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;

@Entity(tableName = "menu_table")
public class Menu {

    @NonNull
    @ColumnInfo(name = "_ID")
    @PrimaryKey
    private String id;

    private Meal meal;

    @ColumnInfo(name = "_PRICE")
    private Double price;

    @ColumnInfo(name = "_SECTION")
    private String section;

    public Menu() {

    }


    public Menu(Meal meal) {
        KeyValue prices = new KeyValue(meal.getPrice().keySet(),meal.getPrice().values());
        this.price = prices.getValue().get(0);
        this.meal = meal;
        this.section = meal.getTags().get(0).getEnName();
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
