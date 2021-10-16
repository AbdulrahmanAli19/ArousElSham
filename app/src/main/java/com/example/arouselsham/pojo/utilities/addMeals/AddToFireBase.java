package com.example.arouselsham.pojo.utilities.addMeals;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.arouselsham.pojo.utilities.Common;
import com.example.arouselsham.pojo.model.male.Meal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class AddToFireBase {

    private static final String TAG = "Cannot invoke method length() on null object";

    private static Map<String, Double> thePrices = new HashMap<>();
    private static List<Meal> meals = new ArrayList<>();
    private static List<String> enNames, arNames;
    private static List<Double> syrianBrad, frenchBrad, saro5Bread;
    private static List<Double> kg, halfKg, rob3kg, tomnKg;
    private static List<Double> mediumPrices, largePrices;
    private static List<Double> oneChicken, halfChicken, rob3Chicken;
    private static List<Double> priceByOne;


    public static List<Meal> fillTheList() {
        enNames = getTextFromString(TheStrings.enName);
        arNames = getTextFromString(TheStrings.arName);
        kg = getPriceFromString(TheStrings.kgPrice);
        halfKg = getPriceFromString(TheStrings.halfKgPrice);
        rob3kg = getPriceFromString(TheStrings.rob3KgPrice);
        priceByOne = getPriceFromString(TheStrings.price);

        for (int i = 0; i < enNames.size(); i++) {

            if (kg.get(i) > 0.0 && kg.get(i) != null)
                thePrices.put(Common.KILOGRAM, kg.get(i));

            if (halfKg.get(i) > 0.0 && halfKg.get(i) != null)
                thePrices.put(Common.HALF_KILOGRAMS, halfKg.get(i));

            if (rob3kg.get(i) > 0.0 && rob3kg.get(i) != null)
                thePrices.put(Common.QUARTER_KILOGRAMS, rob3kg.get(i));

            if (priceByOne.get(i) > 0.0 && priceByOne.get(i) != null)
                thePrices.put(Common.PRICE_BY_ONE, priceByOne.get(i));


           /* if (kg.get(i) > 0.0 && kg.get(i) != null)
                thePrices.put(Common.kilograms, kg.get(i));

            if (halfKg.get(i) > 0.0 && halfKg.get(i) != null)
                thePrices.put(Common.halfKilograms, halfKg.get(i));*/


            meals.add(new Meal(arNames.get(i), enNames.get(i), thePrices, enNames.get(i),
                    arNames.get(i), TheStrings.getToppings(), TheStrings.getTags()));

            meals.get(i).setId(meals.get(i).getTags().get(0).getEnName().toLowerCase(Locale.ROOT).trim() + i);

            uploadData(meals.get(i));
            uploadImage(i);
            thePrices.clear();
        }
        return meals;
    }

    private static void uploadData(Meal meal) {

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(meal.getTags().get(0).getEnName())
                .collection("MenuItems")
                .document(meal.getEnName())
                .set(meal)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));

    }

    private static void uploadImage(int i) {

        StorageReference storage;
        StorageReference ref;

        ///TODO: get the image from mobile

        Uri file = Uri.parse("android.resource://com.example.arouselsham/" + TheStrings.getImages().get(i));

        storage = FirebaseStorage.getInstance()
                .getReference("Menu Images/" + meals.get(i).getTags().get(0).getEnName());

        String id = enNames.get(i).toLowerCase(Locale.ROOT).trim() + i;

        String tag = meals.get(0).getTags().get(0).getEnName();

        ref = storage.child(tag + id);

        int finalI = i;
        ref.putFile(file)
                .addOnSuccessListener(taskSnapshot -> {

                    if (taskSnapshot.getMetadata() != null) {

                        if (taskSnapshot.getMetadata().getReference() != null) {

                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                            result.addOnSuccessListener(uri -> {

                                String imageUrl = uri.toString();
                                meals.get(finalI).setImageUrl(imageUrl);
                                Log.d(TAG, "the URL is : " + imageUrl);
                                updateData(meals.get(finalI));

                            });

                        }

                    }
                })
                .addOnFailureListener(e -> Log.d(TAG, " onFailure : " + e.getMessage()));

    }


    private static void updateData(Meal meal) {

        Map<String, Object> imageURL = new HashMap<>();
        imageURL.put("imageUrl", meal.getImageUrl());

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(meal.getTags().get(0).getEnName())
                .collection("MenuItems")
                .document(meal.getEnName())
                .update(imageURL)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));

    }


    private static void deleteData (Meal meal){
        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(meal.getTags().get(0).getEnName())
                .collection("MenuItems")
                .document(meal.getEnName())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Log.d(TAG, meal.getEnName()+" Deleted.");
                        else
                            Log.d(TAG, " Error: "+task.getException());
                    }
                });
    }

    private static List<String> getTextFromString(String text) {
        String meal = "";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == '\n') {

                if (meal.length() > 1) {
                    list.add(meal);
                    meal = "";
                }

            } else {
                meal += String.valueOf(text.charAt(i));
            }
        }
        return list;
    }

    private static List<Double> getPriceFromString(String text) {
        String meal = "";
        List<Double> list = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {

            if (text.charAt(i) == '\n') {

                if (meal.length() > 0) {
                    if (meal.equals("-") || meal == "-")
                        meal = "0";
                    list.add(Double.valueOf(meal));
                    meal = "";
                }

            } else {
                meal += String.valueOf(text.charAt(i));
            }
        }
        return list;
    }

}
