package com.example.arouselsham.pojo;

import com.example.arouselsham.pojo.model.maleModels.PriceByBreadTypes;
import com.example.arouselsham.pojo.model.maleModels.PriceByKilogram;
import com.example.arouselsham.pojo.model.maleModels.PriceBySize;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static boolean isArabicEnabled = false;
    public static final String CUSTOMER_INFO_REFERENCE = "CustomerInfo";

    public static List<String> getKilogramsTags(PriceByKilogram priceByKilogram) {
        List<String> tags = new ArrayList<>();

        if (priceByKilogram.getTomnKilograms() != 0)
            tags.add("⅛ Kilogram");

        if (priceByKilogram.getQuarterKilograms() != 0)
            tags.add("¼ Kilogram");

        if (priceByKilogram.getHalfKilograms() != 0)
            tags.add("½ Kilogram");

        if (priceByKilogram.getKilograms() != 0)
            tags.add("1 Kilogram");
        return tags;
    }

    public static List<String> getSizeTags(PriceBySize priceBySize) {
        List<String> tags = new ArrayList<>();

        if (priceBySize.getMediumSizePrice() != 0)
            tags.add("Medium");

        if (priceBySize.getBigSizePrice() != 0)
            tags.add("Large");
        return tags;
    }

    public static List<String> getBreadTags(PriceByBreadTypes priceByBreadTypes) {
        List<String> tags = new ArrayList<>();

        if (priceByBreadTypes.getSyrianPrice() != 0)
            tags.add("Arabic bread");
        if (priceByBreadTypes.getFrenchPrice() != 0)
            tags.add("French bread");
        if (priceByBreadTypes.getSaro5Price() != 0)
            tags.add("Saro5 bread");
        return tags;
    }

    public static List<String> getPeaceTags() {
        List<String> tags = new ArrayList<>();
        tags.add("¼ Chicken");
        tags.add("½ Chicken");
        tags.add("1 Chicken");
        return tags;
    }

}
