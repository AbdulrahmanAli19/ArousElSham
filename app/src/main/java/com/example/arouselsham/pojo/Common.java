package com.example.arouselsham.pojo;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;

import androidx.room.Room;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static boolean isArabicEnabled = false;
    public static final String CUSTOMER_INFO_REFERENCE = "CustomerInfo";
    public static final String dbName = "ArousEl-ShamDB";

    public static final String syrianPrice = "Arabic bread";
    public static final String frenchPrice = "French bread";
    public static final String saro5Price = "Saro5 bread";

    public static final String kilograms = "1 Kilogram";
    public static final String halfKilograms = "½ Kilogram";
    public static final String quarterKilograms = "¼ Kilogram";
    public static final String tomnKilograms = "⅛ Kilogram";

    public static final String priceByOne = "priceByOne";
    public static final String discount = "discount";

    public static final String holeChicken = "¼ Chicken";
    public static final String halfChicken = "½ Chicken";
    public static final String quarterChicken = "1 Chicken";

    public static final String mediumPrice = "Medium";
    public static final String largPrice = "Large";

    @SuppressLint("RestrictedApi")
    public static DataBaseManger dataBaseManger = Room
            .databaseBuilder(getApplicationContext(),
                    DataBaseManger.class,
                    dbName)
            .allowMainThreadQueries().build();

    public static List<PriceOption> getPriceOptions(Meal meal) {
        List<PriceOption> models = new ArrayList<>();

        KeyValue keyValue = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        for (int i = 0; i < keyValue.getValue().size(); i++) {
            models.add(new PriceOption(keyValue.getKey().get(i), keyValue.getValue().get(i)));
        }

        return models;
    }

}
