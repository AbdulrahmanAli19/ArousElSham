package com.example.arouselsham.pojo.utilities.addMeals;

import com.example.arouselsham.pojo.model.male.MenuTags;
import com.example.arouselsham.pojo.model.male.MenuTopping;

import java.util.ArrayList;
import java.util.List;

public class TheStrings {

    public final static String arName = "كنافة على الفحم\n" +
            "كنافه نابلسية\n" +
            "كنافه بالجبنة\n" +
            "كنافه شوكولاتة\n";

    public final static String enName = "Kunefe On Charcoal\n" +
            "Nabulsi Kunefe\n" +
            "Cheese Kunefe\n" +
            "Chocolate Kunefe\n";

    public static List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();


        return images;
    }

    public static List<MenuTags> getTags() {
        List<MenuTags> tags = new ArrayList<>();
        tags.add(new MenuTags("Kunefe", "كنافة"));
        return tags;
    }

    public static List<MenuTopping> getToppings() {
        List<MenuTopping> toppings = new ArrayList<>();
        //toppings.add(new MenuTopping("حار", "Spicy", 0.0));
        return toppings;
    }


    public final static String oneChicken = "110\n";
    public final static String halfChicken = "60\n";
    public final static String rob3Chicken = "37.33\n";

    public final static String meduim = "30\n" +
            "35\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "50\n" +
            "50\n" +
            "55\n" +
            "55\n" +
            "60\n" +
            "55\n";
    public final static String large = "35\n" +
            "40\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "45\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "55\n" +
            "55\n" +
            "60\n" +
            "60\n" +
            "70\n" +
            "65\n";

    public final static String kgPrice = "-\n" +
            "-\n" +
            "80\n" +
            "75\n";

    public final static String halfKgPrice = "-\n" +
            "-\n" +
            "40\n" +
            "40\n";

    public final static String rob3KgPrice = "-\n" +
            "-\n" +
            "20\n" +
            "20\n";

    public final static String tomnKgPrice = "40\n" +
            "50\n" +
            "25\n" +
            "30\n" +
            "-\n" +
            "-\n" +
            "-\n" +
            "-\n";

    public final static String price = "45\n" +
            "60\n" +
            "-\n" +
            "-\n";

    public final static String syrianBreadPrice = "20\n" +
            "30\n" +
            "25\n" +
            "20\n" +
            "15\n" +
            "15\n" +
            "30\n" +
            "22\n" +
            "20\n" +
            "20\n" +
            "22\n" +
            "-\n" +
            "-\n" +
            "-\n" +
            "10\n" +
            "10\n";

    public final static String frenchBreadPrice = "25\n" +
            "35\n" +
            "30\n" +
            "22\n" +
            "18\n" +
            "20\n" +
            "-\n" +
            "24\n" +
            "22\n" +
            "22\n" +
            "24\n" +
            "25\n" +
            "25\n" +
            "25\n" +
            "12\n" +
            "12\n";

    public final static String saro5Price = "-\n" +
            "-\n" +
            "-\n" +
            "30\n" +
            "25\n" +
            "-\n" +
            "-\n" +
            "32\n" +
            "30\n" +
            "30\n" +
            "32\n" +
            "-\n" +
            "-\n" +
            "-\n" +
            "-\n" +
            "20\n";

}
