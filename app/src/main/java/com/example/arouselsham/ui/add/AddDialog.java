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
        MealModel mealModel = new MealModel();
        if (tags.get(0).equals(arMenu.get(0)) || tags.get(0).equals(enMenu.get(0))) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            MealModel.Topping topping;
            mealModel.setArName(edtArName.getText().toString());
            mealModel.setEnName(edtEnName.getText().toString());
            mealModel.setPriceByBreadTypes(
                    new MealModel.PriceByBreadTypes(
                            Double.valueOf(edtSoriPrice.getText().toString()),
                            Double.valueOf(edtFrenchPrice.getText().toString()),
                            0.0
                    ));
            mealModel.setEnName(edtEnName.getText().toString());

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

        }else if (tags.get(0).equals(arMenu.get(13)) || tags.get(0).equals(enMenu.get(13))) {
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

        }else if (tags.get(0).equals(arMenu.get(13)) || tags.get(0).equals(enMenu.get(13))) {
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
