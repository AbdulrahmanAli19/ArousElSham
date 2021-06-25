package com.example.arouselsham.ui.add;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.MenuClass;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuTags;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDialog extends DialogFragment {

    private static final String TAG = "AddDialog";

    private ArrayList<String> tags = new ArrayList<>();

    private List<String> arMenu;

    private List<String> enMenu;

    @BindView(R.id.add_spinner)
    Spinner addSpinner;

    @BindView(R.id.et_name_ar)
    EditText edtArName;

    @BindView(R.id.et_name_en)
    EditText edtEnName;

    @BindView(R.id.et_price)
    EditText edtPrice;

    @BindView(R.id.et_sori_price)
    EditText edtSoriPrice;

    @BindView(R.id.et_french_price)
    EditText edtFrenchPrice;

    @BindView(R.id.et_saro5_price)
    EditText edtSaro5Price;

    @BindView(R.id.et_kg_price)
    EditText edtKgPrice;

    @BindView(R.id.et_half_kg_price)
    EditText edtHalfKgPrice;

    @BindView(R.id.et_quarter_kg_price)
    EditText edtQuarterKgPrice;

    @BindView(R.id.et_tomn_kg_price)
    EditText edtTomnKgPrice;

    @BindView(R.id.et_medium_size)
    EditText edtMediumPrice;

    @BindView(R.id.et_big_size)
    EditText edtBigPrice;

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_fragment, null);
        ButterKnife.bind(this, view);

        addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                defaultView();
                tags.clear();
                tags.add(arMenu.get(position));
                logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document("MenuMainTags")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    MenuClass menuClass = new MenuClass();

                    if (!queryDocumentSnapshots.getData().isEmpty())
                        menuClass = queryDocumentSnapshots.toObject(MenuClass.class);

                    arMenu = menuClass.getArTags();
                    enMenu = menuClass.getEnTags();

                    if (!arMenu.isEmpty()) {
                        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, arMenu);
                        addSpinner.setAdapter(spinnerAdapter);
                    }


                })
                .addOnFailureListener(e -> {
                    Log.d(TAG, "onCreateDialog: " + e.getMessage());
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });


        builder.setView(view)
                .setTitle(R.string.add_a_new_meal)
                .setNegativeButton(R.string.cancel, (dialog, which) -> {

                })
                .setPositiveButton(R.string.add, (dialog, which) -> {
                    addToFireBase();
                });
        return builder.create();
    }

    private void addToFireBase() {

        startTheMenu();


        if (tags.get(0).equals(arMenu.get(0)) || tags.get(0).equals(enMenu.get(0))) {


           /* String arName = edtArName.getText().toString();
            String enName = edtEnName.getText().toString();


            List<MealModel.Topping> toppings = new ArrayList<>();
            toppings.add(new MealModel.Topping("ar", "en", 22.2));
            toppings.add(new MealModel.Topping("ar1", "en1", 222.2));
            toppings.add(new MealModel.Topping("ar2", "en2", 223.2));

            MealModel.PriceByBreadTypes priceByBreadType =
                    new MealModel.PriceByBreadTypes(
                            Double.parseDouble(edtSoriPrice.getText().toString()),
                            Double.parseDouble(edtFrenchPrice.getText().toString()),
                            Double.parseDouble(edtSoriPrice.getText().toString()));

            MealModel.PriceByKilogram priceByKilogram =
                    new MealModel.PriceByKilogram(
                            Double.parseDouble(edtKgPrice.getText().toString()),
                            Double.parseDouble(edtHalfKgPrice.getText().toString()),
                            0.0, 0.0);

            MealModel mealModel = new MealModel(tags, arName, enName, priceByBreadType, toppings);
            mealModel.setPriceByByKilogram(priceByKilogram);

            uploadData(enName, enMenu.get(0), mealModel);*/


        } else if (tags.get(0).equals(arMenu.get(1)) || tags.get(0).equals(enMenu.get(1))) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(2)) || tags.get(0).equals(enMenu.get(2))) {
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(3)) || tags.get(0).equals(enMenu.get(3))) {
            ///TODO: ADD THE TAGS
            edtSoriPrice.setVisibility(View.VISIBLE);
            edtSaro5Price.setVisibility(View.VISIBLE);
            edtFrenchPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(4)) || tags.get(0).equals(enMenu.get(4))) {
            ///TODO: ADD THE TAGS
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(5)) || tags.get(0).equals(enMenu.get(5))) {
            ///TODO: ADD THE TAGS
            edtMediumPrice.setVisibility(View.VISIBLE);
            edtBigPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(6)) || tags.get(0).equals(enMenu.get(6))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(7)) || tags.get(0).equals(enMenu.get(7))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(8)) || tags.get(0).equals(enMenu.get(8))) {
            edtKgPrice.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtQuarterKgPrice.setVisibility(View.VISIBLE);
            edtTomnKgPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(9)) || tags.get(0).equals(enMenu.get(9))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(10)) || tags.get(0).equals(enMenu.get(10))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(12)) || tags.get(0).equals(enMenu.get(12))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(11)) || tags.get(0).equals(enMenu.get(11))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(13)) || tags.get(0).equals(enMenu.get(13))) {
            edtKgPrice.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtQuarterKgPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(14)) || tags.get(0).equals(enMenu.get(14))) {
            tags.add("إضافات");
            edtPrice.setVisibility(View.VISIBLE);
        } else {
            Log.d(TAG, "logic: ERROR");
        }
    }

    private void startTheMenu() {

        List<String> enNames = new ArrayList<>();
        List<String> arNames = new ArrayList<>();

        //List<MealModel.PriceByBreadTypes> priceByBreadTypes = new ArrayList<>();
        //List<MealModel.PriceByPiece> priceByPieces = new ArrayList<>();
        //List<MealModel.PriceByKilogram> priceByKilograms = new ArrayList<>();
       // List<MealByOne> mealByOnes = new ArrayList<>();
        //List<MealModel.PriceBySize> priceBySizes = new ArrayList<>();


        List<List<MenuTags>> tags = new ArrayList<>();

        List<MenuTopping> menuToppings = new ArrayList<>();

        /*arNames.add("حواوشي لحمه");
        enNames.add("Beef Hawawshi");
        List<MealModel.Tags> tags1 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(30.0));
        tags1.add(new MealModel.Tags("Hawawshi", "الحواوشي"));
        tags1.add(new MealModel.Tags("Savory", "حادق"));
        tags1.add(new MealModel.Tags("Beef", "لحم"));
        tags.add(tags1);

        arNames.add("حواوشي سجق");
        enNames.add("Sausage Hawawshi");
        List<MealModel.Tags> tags2 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(30.0));
        tags2.add(new MealModel.Tags("Hawawshi", "الحواوشي"));
        tags2.add(new MealModel.Tags("Savory", "حادق"));
        tags2.add(new MealModel.Tags("sausage", "سجق"));
        tags.add(tags2);

        arNames.add("حواوشي بسطرمة");
        enNames.add("Pastrami Hawawshi");
        List<MealModel.Tags> tags3 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(35.0));
        tags3.add(new MealModel.Tags("Hawawshi", "الحواوشي"));
        tags3.add(new MealModel.Tags("Savory", "حادق"));
        tags3.add(new MealModel.Tags("Pastrami", "بسطرمة"));
        tags.add(tags3);

        arNames.add("حواوشي شاورما فراخ");
        enNames.add("Chicken Shawarma Hawawshi");
        List<MealModel.Tags> tags4 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(40.0));
        tags4.add(new MealModel.Tags("Hawawshi", "الحواوشي"));
        tags4.add(new MealModel.Tags("Savory", "حادق"));
        tags4.add(new MealModel.Tags("Chicken", "فراخ"));
        tags4.add(new MealModel.Tags("Shawarma", "شاورما"));
        tags.add(tags4);

        arNames.add("حواوشي شاورما لحم");
        enNames.add("Beef Shawarma Hawawshi");
        List<MealModel.Tags> tags5 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(45.0));
        tags5.add(new MealModel.Tags("Hawawshi", "الحواوشي"));
        tags5.add(new MealModel.Tags("Savory", "حادق"));
        tags5.add(new MealModel.Tags("Beef", "لحم"));
        tags5.add(new MealModel.Tags("Shawarma", "شاورما"));
        tags.add(tags5);


        arNames.add("طبق كول سلو");
        enNames.add("Coleslaw");
        List<MealModel.Tags> tags6 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(10.0));
        tags6.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags6);

        arNames.add("طبق تومية");
        enNames.add("Tomiya");
        List<MealModel.Tags> tags7 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(10));
        tags7.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags7);

        arNames.add("طبق مخلل");
        enNames.add("Pickles");
        List<MealModel.Tags> tags12 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(5));
        tags12.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags12);

        arNames.add("طبق أرز بسمتي");
        enNames.add("Basmati rice dish");
        List<MealModel.Tags> tags8 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(12));
        tags8.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags8);

        arNames.add("فلافل");
        enNames.add("Falafel");
        List<MealModel.Tags> tags9 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(.50));
        tags9.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags9);

        arNames.add("طبق بطاطس");
        enNames.add("French Fries");
        List<MealModel.Tags> tags11 = new ArrayList<>();
        priceByOnes.add(new MealModel.PriceByOne(10));
        tags11.add(new MealModel.Tags("Topping", "إضافات"));
        tags.add(tags11);





        MealModel mealModel;
        for (int i = 0; i < enNames.size(); i++) {

            mealModel = new MealModel(tags.get(i), arNames.get(i), enNames.get(i), priceByOnes.get(i), toppings);
            uploadData(enNames.get(i), tags.get(i).get(0).getEnName(), mealModel);
        }*/

    }

    private void uploadData(String enName, String tag, Meal meal) {

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(tag)
                .collection("MenuItems")
                .document(enName)
                .set(meal)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));
    }

    private void removeData(String names, String tag) {

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(tag)
                .collection("MenuItems")
                .document(names)
                .delete()
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));
    }

    private void updateData(String names, String tag, Object mealModel) {

        Map<String, Object> map = parameters(mealModel);

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(tag)
                .collection("MenuItems")
                .document(names)
                .update(map)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));
    }

    public static Map<String, Object> parameters(Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
            }
        }
        return map;
    }

    private void logic() {

        if (tags.get(0).equals(arMenu.get(0)) || tags.get(0).equals(enMenu.get(0))) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            edtSoriPrice.setVisibility(View.VISIBLE);
            edtFrenchPrice.setVisibility(View.VISIBLE);
            edtSaro5Price.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtKgPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(1)) || tags.get(0).equals(enMenu.get(1))) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(2)) || tags.get(0).equals(enMenu.get(2))) {
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(3)) || tags.get(0).equals(enMenu.get(3))) {
            ///TODO: ADD THE TAGS
            edtSoriPrice.setVisibility(View.VISIBLE);
            edtSaro5Price.setVisibility(View.VISIBLE);
            edtFrenchPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(4)) || tags.get(0).equals(enMenu.get(4))) {
            ///TODO: ADD THE TAGS
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(5)) || tags.get(0).equals(enMenu.get(5))) {
            ///TODO: ADD THE TAGS
            edtMediumPrice.setVisibility(View.VISIBLE);
            edtBigPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(6)) || tags.get(0).equals(enMenu.get(6))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(7)) || tags.get(0).equals(enMenu.get(7))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(8)) || tags.get(0).equals(enMenu.get(8))) {
            edtKgPrice.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtQuarterKgPrice.setVisibility(View.VISIBLE);
            edtTomnKgPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(9)) || tags.get(0).equals(enMenu.get(9))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(10)) || tags.get(0).equals(enMenu.get(10))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(12)) || tags.get(0).equals(enMenu.get(12))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(11)) || tags.get(0).equals(enMenu.get(11))) {
            edtPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(13)) || tags.get(0).equals(enMenu.get(13))) {
            edtKgPrice.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtQuarterKgPrice.setVisibility(View.VISIBLE);

        } else if (tags.get(0).equals(arMenu.get(14)) || tags.get(0).equals(enMenu.get(14))) {
            tags.add("إضافات");
            edtPrice.setVisibility(View.VISIBLE);
        } else {
            Log.d(TAG, "logic: ERROR");
        }
    }

    private void defaultView() {
        edtPrice.setVisibility(View.GONE);
        edtSoriPrice.setVisibility(View.GONE);
        edtFrenchPrice.setVisibility(View.GONE);
        edtSaro5Price.setVisibility(View.GONE);
        edtMediumPrice.setVisibility(View.GONE);
        edtBigPrice.setVisibility(View.GONE);
        edtKgPrice.setVisibility(View.GONE);
        edtHalfKgPrice.setVisibility(View.GONE);
        edtQuarterKgPrice.setVisibility(View.GONE);
        edtTomnKgPrice.setVisibility(View.GONE);
    }

}
