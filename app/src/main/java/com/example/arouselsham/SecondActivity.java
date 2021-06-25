package com.example.arouselsham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.ui.home.MealAdapter;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "Cannot invoke method length() on null object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        List<Meal> meals = (List<Meal>) getIntent().getSerializableExtra("Meals");
        Log.d(TAG, "onCreate: " + meals.get(0).getArName());

        RecyclerView recyclerView = findViewById(R.id.meals_recyclerview);
        MealAdapter adapter = new MealAdapter(this, meals);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }
}