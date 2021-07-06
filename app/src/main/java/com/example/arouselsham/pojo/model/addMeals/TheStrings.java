package com.example.arouselsham.pojo.model.addMeals;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MenuTags;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;

import java.util.ArrayList;
import java.util.List;

public class TheStrings {

    public final static String arName = "كول سلو\n" +
            "طبق تومية\n" +
            "مخلل\n" +
            "أرز بسمتي\n" +
            "فلافل\n" +
            "بطاطس\n";

    public final static String enName = "Coleslaw\n" +
            "Tomiya\n" +
            "Pickles\n" +
            "Basmati Rice\n" +
            "Falafel\n" +
            "French Fries\n";

    public static List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();
//        images.add(R.drawable.coleslaw);
//        images.add(R.drawable.tomiya);
//        images.add(R.drawable.pickles);
//        images.add(R.drawable.basmati_rice);
//        images.add(R.drawable.falafel);
//        images.add(R.drawable.french_fries);


        return images;
    }

    public static List<MenuTags> getTags() {
        List<MenuTags> tags = new ArrayList<>();
        tags.add(new MenuTags("Topping", "إضافات"));
        return tags;
    }

    public static List<MenuTopping> getToppings() {
        List<MenuTopping> toppings = new ArrayList<>();
       /* toppings.add(new MenuTopping("حار", "Spicy", 0.0));
        toppings.add(new MenuTopping("موتزاريلا","Mozzarella",5.0));*/
        return toppings;
    }


    public final static String oneChicken = "110\n";
    public final static String halfChicken = "60\n";
    public final static String rob3Chicken = "37.33\n" ;

    public final static String kgPrice = "120\n" +
            "240\n" +
            "220\n";

    public final static String halfKgPrice = "95\n" +
            "120\n" +
            "-\n";

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

    public final static String price = "10\n" +
            "10\n" +
            "5\n" +
            "12\n" +
            "0.5\n" +
            "10\n";

    public final static String syrianBreadPrice = "20\n" +
            "30\n" +
            "-\n";

    public final static String frenchBreadPrice = "25\n" +
            "35\n" +
            "-\n";

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
