package com.example.arouselsham.ui.section;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.SandwichViewHolder> implements Serializable {
    private static final String TAG = "Cannot invoke method length() on null object";
    private final OnItemClickListener onItemClickListener;
    private final Context mContext;
    private List<Meal> meals = new ArrayList<>();

    public MealAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SandwichViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.meal_layout, parent, false);
        return new SandwichViewHolder(view, onItemClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SandwichViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.txtMealName.setText(meal.getEnName());

        Log.d(TAG, "meal : " + meal.toString());

        KeyValue prices = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        Picasso.get().load(meal.getImageUrl()).into(holder.imageView);


        if (prices.getKey().get(0).equals(Common.PRICE_BY_ONE) || prices.getKey().get(0) == Common.PRICE_BY_ONE) {

            holder.txtFrechPrice.setText(prices.getValue().get(0) + " EGP");

        } else {
            holder.txtFrechPrice.setText(R.string.price_by_selection);
        }


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class SandwichViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onItemClickListener;
        TextView txtMealName, txtFrechPrice;
        ImageView imageView;

        public SandwichViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_snadwich_name);
            txtFrechPrice = itemView.findViewById(R.id.txt_french_price);
            imageView = itemView.findViewById(R.id.meal_image);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(getBindingAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
