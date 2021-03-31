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
import com.example.arouselsham.pojo.model.MealModel;
import com.example.arouselsham.pojo.model.MenuClass;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        List<MealModel.PriceByBreadTypes> priceByBreadTypes = new ArrayList<>();
        //List<MealModel.PriceByKilogram> priceByKilograms = new ArrayList<>();
        //List<MealModel.PriceByOne> priceByOnes = new ArrayList<>();


        List<List<MealModel.Tags>> tags = new ArrayList<>();

        List<MealModel.Topping> toppings = new ArrayList<>();
        toppings.add(new MealModel.Topping("حار", "Spicyy", 0.0));

        arNames.add("فاهيتا فراخ");
        enNames.add("Fajita chicken");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(25.0, 30.0, 20.0));
        List<MealModel.Tags> tags1 = new ArrayList<>();
        tags1.add(new MealModel.Tags("Sandwiches Al-Arous", "سندوتشات العروس"));
        tags1.add(new MealModel.Tags("Fajita", "فاهيتا"));
        tags1.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags1.add(new MealModel.Tags("chicken", "فراخ"));
        tags1.add(new MealModel.Tags("Savory ", "حادق"));
        tags1.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags1);

        arNames.add("ميكسيكانو فراخ");
        enNames.add("Mexican Chicken");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(22.0, 30.0, 20.0));
        List<MealModel.Tags> tags2 = new ArrayList<>();
        tags2.add(new MealModel.Tags("Sandwiches Al-Arous", "سندوتشات العروس"));
        tags2.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags2.add(new MealModel.Tags("Mexican ", "ميكسيكانو"));
        tags2.add(new MealModel.Tags("chicken", "فراخ"));
        tags2.add(new MealModel.Tags("Savory ", "حادق"));
        tags2.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags2);

        arNames.add("كبده فراخ");
        enNames.add("Chicken liver");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(22.0, 25.0, 15.0));
        List<MealModel.Tags> tags3 = new ArrayList<>();
        tags3.add(new MealModel.Tags("Sandwiches Al-Arous", "سندوتشات العروس"));
        tags3.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags3.add(new MealModel.Tags("liver", "كبده"));
        tags3.add(new MealModel.Tags("chicken", "فراخ"));
        tags3.add(new MealModel.Tags("Savory ", "حادق"));
        tags3.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags3);

        arNames.add("كفته بلدي");
        enNames.add("Kebab");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(20.0, 0.0, 15.0));
        List<MealModel.Tags> tags4 = new ArrayList<>();
        tags4.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags4.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags4.add(new MealModel.Tags("Beef", "لحم"));
        tags4.add(new MealModel.Tags("Kebab", "كفته"));
        tags4.add(new MealModel.Tags("Savory ", "حادق"));
        tags4.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags4);

        arNames.add("كباب ضاني");
        enNames.add("Lamb kebab");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(0.0, 0.0, 30.0));
        List<MealModel.Tags> tags5 = new ArrayList<>();
        tags5.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags5.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags5.add(new MealModel.Tags("Beef", "لحم"));
        tags5.add(new MealModel.Tags("Kebab", "كباب"));
        tags5.add(new MealModel.Tags("Lamb", "ضاني"));
        tags5.add(new MealModel.Tags("Savory ", "حادق"));
        tags5.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags5);

        arNames.add("شيش طاووق");
        enNames.add("Chicken Grill");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(24.0, 32.0, 22.0));
        List<MealModel.Tags> tags6 = new ArrayList<>();
        tags6.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags6.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags6.add(new MealModel.Tags("Grill", "شيش"));
        tags6.add(new MealModel.Tags("chicken", "فراخ"));
        tags6.add(new MealModel.Tags("Savory ", "حادق"));
        tags6.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags6);

        arNames.add("بانية");
        enNames.add("Baneh");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(22.0, 30.0, 20.0));
        List<MealModel.Tags> tags7 = new ArrayList<>();
        tags7.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags7.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags7.add(new MealModel.Tags("chicken", "فراخ"));
        tags7.add(new MealModel.Tags("Savory ", "حادق"));
        tags7.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags7);

        arNames.add("فراخ كرسبي");
        enNames.add("Crispy Chicken");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(22.0, 30.0, 20.0));
        List<MealModel.Tags> tags8 = new ArrayList<>();
        tags8.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags8.add(new MealModel.Tags("Sandwiches", "سندوتشات"));
        tags8.add(new MealModel.Tags("Crispy", "كرسبي"));
        tags8.add(new MealModel.Tags("chicken", "فراخ"));
        tags8.add(new MealModel.Tags("Savory ", "حادق"));
        tags8.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags8);

        arNames.add("زنجر");
        enNames.add("Zinger");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(20.0, 22.0, 30.0));
        List<MealModel.Tags> tags9 = new ArrayList<>();
        tags9.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags9.add(new MealModel.Tags("Burger", "برجر"));
        tags9.add(new MealModel.Tags("chicken", "فراخ"));
        tags9.add(new MealModel.Tags("Savory ", "حادق"));
        tags9.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags9);

        arNames.add("تشيز برجر لحم");
        enNames.add("Beef cheese burger");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(0.0, 30.0, 0.0));
        List<MealModel.Tags> tags10 = new ArrayList<>();
        tags10.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags10.add(new MealModel.Tags("Burger", "برجر"));
        tags10.add(new MealModel.Tags("beef", "لحم"));
        tags10.add(new MealModel.Tags("cheese", "تشيز"));
        tags10.add(new MealModel.Tags("Savory ", "حادق"));
        tags10.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags10);

        arNames.add("برجر فراخ");
        enNames.add("Chicken burger");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes(0.0, 25.0, 0.0));
        List<MealModel.Tags> tags11 = new ArrayList<>();
        tags11.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags11.add(new MealModel.Tags("Burger", "برجر"));
        tags11.add(new MealModel.Tags("Chicken", "فراخ"));
        tags11.add(new MealModel.Tags("Savory ", "حادق"));
        tags11.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags11);

        arNames.add("برجر فراخ رومانو");
        enNames.add("Romano Chicken Burger");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes());
        List<MealModel.Tags> tags12 = new ArrayList<>();
        tags12.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags12.add(new MealModel.Tags("Burger", "برجر"));
        tags12.add(new MealModel.Tags("Chicken", "فراخ"));
        tags12.add(new MealModel.Tags("Romano", "رومانو"));
        tags12.add(new MealModel.Tags("Savory ", "حادق"));
        tags12.add(new MealModel.Tags("lunch", "غداء"));
        tags.add(tags12);

        arNames.add("فلافل عربي");
        enNames.add("Falafel Arabic");
        priceByBreadTypes.add(new MealModel.PriceByBreadTypes());
        List<MealModel.Tags> tags13 = new ArrayList<>();
        tags13.add(new MealModel.Tags("Meals Al-Arous", "وجبات العروس"));
        tags13.add(new MealModel.Tags("Falafel", "فلافل"));
        tags13.add(new MealModel.Tags("vegan", "نباتي"));
        tags13.add(new MealModel.Tags("Savory ", "حادق"));
        tags13.add(new MealModel.Tags("Breakfast", "فطار"));
        tags.add(tags13);


        MealModel mealModel;
        for (int i = 0; i < enNames.size(); i++) {

            mealModel = new MealModel(tags.get(i), arNames.get(i), enNames.get(i), priceByBreadTypes.get(i), toppings);
            uploadData(enNames.get(i), tags.get(i).get(0).getEnName(), mealModel);
        }

    }

    private void uploadData(String enName, String tag, MealModel mealModel) {

        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(tag)
                .collection("MenuItems")
                .document(enName)
                .set(mealModel)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "onSuccess: done"))
                .addOnFailureListener(e ->
                        Log.d(TAG, "onFailure: " + e.getMessage()));
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
