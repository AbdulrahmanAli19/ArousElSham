package com.example.arouselsham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.arouselsham.pojo.model.maleModels.MealModel;
import com.example.arouselsham.ui.home.MealAdapter;
import com.example.arouselsham.ui.home.SandwichAdapter;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "Cannot invoke method length() on null object";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        List<MealModel> mealModels = (List<MealModel>) getIntent().getSerializableExtra("Meals");
        Log.d(TAG, "onCreate: "+mealModels.get(0).getArName());
        if (mealModels.get(0).getTags().get(0).getEnName().equals("Sandwiches Al-Arous")){
            RecyclerView recyclerView = findViewById(R.id.meals_recyclerview);
            SandwichAdapter adapter = new SandwichAdapter(this, mealModels);
            recyclerView.setItemViewCacheSize(0);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
        else {
            RecyclerView recyclerView = findViewById(R.id.meals_recyclerview);
            MealAdapter adapter = new MealAdapter(this, mealModels);
            recyclerView.setItemViewCacheSize(0);
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            manager.setSpanCount(2);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }


    }
}