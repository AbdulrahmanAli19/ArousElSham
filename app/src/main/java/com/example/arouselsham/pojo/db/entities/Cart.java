package com.example.arouselsham.pojo.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.male.MenuTopping;

import java.util.List;

@Entity(tableName = "cart_table")
public class Cart {

    @ColumnInfo(name = "_ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firebaseID;
    private String name;
    private Double price;
    private int mealNum;
    private List<MenuTopping> selectedToppings;
    private String stringSelectedToppings = "";
    private String imageUrl;
    private String selectedItem;

    public Cart() {
    }

    public Cart(String firebaseID, String name, Double price, int mealNum, String imageUrl
            , List<MenuTopping> selectedToppings) {
        this.firebaseID = firebaseID;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.mealNum = mealNum;
        this.selectedToppings = selectedToppings;
        for (int i = 0; i < selectedToppings.size(); i++) {
            stringSelectedToppings += selectedToppings.get(i).getToppingEnName() + "\n";
        }
    }

    public Cart(String firebaseID, String name, Double price, int mealNum, String imageUrl
            , List<MenuTopping> selectedToppings, String selectedItem) {
        this.firebaseID = firebaseID;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.mealNum = mealNum;
        this.selectedToppings = selectedToppings;
        this.selectedItem = selectedItem;
        for (int i = 0; i < selectedToppings.size(); i++) {
            stringSelectedToppings += selectedToppings.get(i).getToppingEnName() + "\n";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMealNum() {
        return mealNum;
    }

    public void setMealNum(int mealNum) {
        this.mealNum = mealNum;
    }

    public List<MenuTopping> getSelectedToppings() {
        return selectedToppings;
    }

    public void setSelectedToppings(List<MenuTopping> selectedToppings) {
        this.selectedToppings = selectedToppings;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }

    public String getStringSelectedToppings() {
        return stringSelectedToppings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStringSelectedToppings(String list) {
        /*String holder = "";
        for (int i = 0; i < list.size(); i++) {
            holder += list.get(i).getToppingEnName() + "\n";
        }*/
        this.stringSelectedToppings = list;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
