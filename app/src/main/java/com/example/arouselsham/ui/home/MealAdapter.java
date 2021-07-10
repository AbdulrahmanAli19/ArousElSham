package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.DetailsActivity;
import com.example.arouselsham.R;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
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
        holder.txtMealName.setText(meal.getEnName());

        KeyValue prices = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        Picasso.get().load(meal.getImageUrl()).into(holder.imageView);

        if (prices.getKey().get(0).equals(Common.priceByOne) || prices.getKey().get(0) == Common.priceByOne) {

            holder.txtFrechPrice.setText(prices.getValue().get(0) + " EGP");

        } else {
            holder.txtFrechPrice.setText(R.string.price_by_selection);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("Meal", (Serializable) meal);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class SandwichViewHolder extends RecyclerView.ViewHolder {
        TextView txtMealName, txtFrechPrice;
        ImageView imageView;
        public SandwichViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_snadwich_name);
            txtFrechPrice = itemView.findViewById(R.id.txt_french_price);
            imageView = itemView.findViewById(R.id.meal_image);
        }
    }
}
