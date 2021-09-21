package com.example.arouselsham.pojo;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Room;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static final String CUSTOMER_INFO_REFERENCE = "CustomerInfo";
    public static final String dbName = "ArousEl-ShamDB";

    public static final String ARABIC_BREAD = "Arabic bread";
    public static final String FRENCH_BREAD = "French bread";
    public static final String SARO_5_BREAD = "Saro5 bread";

    public static final String KILOGRAM = "1 Kilogram";
    public static final String HALF_KILOGRAMS = "½ Kilogram";
    public static final String QUARTER_KILOGRAMS = "¼ Kilogram";
    public static final String TOMN_KILOGRAMS = "⅛ Kilogram";

    public static final String PRICE_BY_ONE = "priceByOne";
    public static final String DISCOUNT = "discount";

    public static final String CHICKEN = "¼ Chicken";
    public static final String HALF_CHICKEN = "½ Chicken";
    public static final String QUARTER_CHICKEN = "1 Chicken";

    public static final String MEDIUM_PRICE = "Medium";
    public static final String LARGE = "Large";

    public static List<PriceOption> getPriceOptions(Meal meal) {
        List<PriceOption> models = new ArrayList<>();

        KeyValue keyValue = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        for (int i = 0; i < keyValue.getValue().size(); i++) {
            models.add(new PriceOption(keyValue.getKey().get(i), keyValue.getValue().get(i)));
        }

        return models;
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage (ImageView imageView, String imageUrl){
        Picasso.get().load(imageUrl).into(imageView);
    }

}
