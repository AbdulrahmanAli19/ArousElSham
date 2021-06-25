package com.example.arouselsham.pojo.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.maleModels.Meal;

@Entity(tableName = "cart_table")
public class Cart {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String arName;
    private String enName;
    private double price;

    public Cart() {

    }

    public Cart(Meal meal) {
        arName = meal.getArName();
        enName = meal.getEnName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
