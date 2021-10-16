package com.example.arouselsham.pojo.db;

import androidx.room.TypeConverter;

import com.example.arouselsham.pojo.model.male.Meal;
import com.example.arouselsham.pojo.model.male.MenuTags;
import com.example.arouselsham.pojo.model.male.MenuTopping;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;


public class Converters {

    @TypeConverter
    public String fromMapToString(HashMap<Object, Object> map) {
        return new Gson().toJson(map);
    }

    @TypeConverter
    public HashMap<Object, Object> fromStringToMap(String string) {
        Type type = new TypeToken<HashMap<Object, Object>>() {
        }.getType();
        return new Gson().fromJson(string, type);
    }

    @TypeConverter
    public List<MenuTags> fromStringToMenuTagsList(String menuTags) {
        Type type = new TypeToken<List<MenuTags>>() {
        }.getType();
        return new Gson().fromJson(menuTags, type);
    }

    @TypeConverter
    public String fromMenuTagsListToString(List<MenuTags> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public String fromMenuToppingListToString(List<MenuTopping> toppings) {
        return new Gson().toJson(toppings);
    }

    @TypeConverter
    public List<MenuTopping> fromStringToMenuToppings(String toppings) {
        Type type = new TypeToken<List<MenuTopping>>() {
        }.getType();
        return new Gson().fromJson(toppings, type);
    }

    @TypeConverter
    public String fromMealListToString(Meal meal) {
        return new Gson().toJson(meal);
    }

    @TypeConverter
    public Meal fromStringToMeal(String toppings) {
        Type type = new TypeToken<Meal>() {
        }.getType();
        return new Gson().fromJson(toppings, type);
    }
}
