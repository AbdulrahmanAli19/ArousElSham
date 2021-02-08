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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDialog extends DialogFragment {

    private static final String TAG = "AddDialog";

    private ArrayList<String> tags = new ArrayList<>();

    private String menu[] ;

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
                tags.add(menu[position]);
                Toast.makeText(getActivity(), menu[position], Toast.LENGTH_SHORT).show();
                logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, menu);
        addSpinner.setAdapter(spinnerAdapter);

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

    }

    private void logic() {
        if (tags.get(0).equals("شاورما العروس")) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            edtSoriPrice.setVisibility(View.VISIBLE);
            edtFrenchPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("إضافات العروس")) {
            tags.add("إضافات");
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("فتات العروس")) {
            tags.add("شاورما");
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("وجبات العروس")) {
            tags.add("حادق");
            tags.add("غداء");
            tags.add("وجبات");
            tags.add("وجبة");
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("سندوتشات العروس")) {
            edtSoriPrice.setVisibility(View.VISIBLE);
            edtSaro5Price.setVisibility(View.VISIBLE);
            edtFrenchPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("الفراخ المشوية على الفحم")) {
        } else if (tags.get(0).equals("بيتزا العروس")) {
            edtMediumPrice.setVisibility(View.VISIBLE);
            edtBigPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("كريب حادق")) {
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("كريب حلو")) {
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("قسم المشويات")) {
            edtKgPrice.setVisibility(View.VISIBLE);
            edtHalfKgPrice.setVisibility(View.VISIBLE);
            edtQuarterKgPrice.setVisibility(View.VISIBLE);
            edtTomnKgPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("فطير حادق")) {
            edtPrice.setVisibility(View.VISIBLE);
        } else if (tags.get(0).equals("فطير حلو")) {
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
