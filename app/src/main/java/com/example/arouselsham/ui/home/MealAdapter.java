package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MealModel;
import com.like.LikeButton;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private Context mContext;
    private List<MealModel> meals ;

    public MealAdapter(Context mContext, List<MealModel> meals) {
        this.mContext = mContext;
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.meal_single_item, parent, false);
        MealViewHolder mealViewHolder = new MealViewHolder(view);
        return mealViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.price.setText(meals.get(position).getPriceByOne().getPrice()+" EGP");
        holder.enName.setText(meals.get(position).getEnName());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView enName, price;
        private CheckBox checkBox;
        private LikeButton likeButton;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            enName = itemView.findViewById(R.id.txt_meal_name);
            price = itemView.findViewById(R.id.txt_meal_price);
        }
    }
}
