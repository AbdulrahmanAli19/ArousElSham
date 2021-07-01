package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.DetailsActivity;
import com.example.arouselsham.R;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.SandwichViewHolder> implements Serializable {
    private static final String TAG = "Cannot invoke method length() on null object";
    private Context mContext;
    private List<Meal> meals;

    public MealAdapter(Context mContext, List<Meal> meals) {
        this.mContext = mContext;
        this.meals = meals;
    }

    @NonNull
    @Override
    public SandwichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.snadwich_layout, parent, false);
        SandwichViewHolder sandwichViewHolder = new SandwichViewHolder(view);
        return sandwichViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SandwichViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.txtMealName.setText(meal.getArName());
        List<PriceOption> priceOptions = Common.getPriceOptions(meal);

        /*if (priceOptions.get(0).getOption() != Common.priceByOne ||
                priceOptions.get(0).getOption().equals(Common.priceByOne)) {
            holder.txtFrechPrice.setText(R.string.price_by_selection);
        } else {
            holder.txtFrechPrice.setText(priceOptions.get(0).getOption() + " EGP");

        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("Meal", (Serializable) meal);
            mContext.startActivity(intent);
        });*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyValue map = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());
                System.out.println("should be prented now");

                for (int i = 0; i < map.getKey().size(); i++) {
                    Log.d(TAG, "test : "+map.getKey().get(i)+" "+map.getValue().get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class SandwichViewHolder extends RecyclerView.ViewHolder {
        TextView txtMealName, txtFrechPrice;

        public SandwichViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_snadwich_name);
            txtFrechPrice = itemView.findViewById(R.id.txt_french_price);
        }
    }
}
