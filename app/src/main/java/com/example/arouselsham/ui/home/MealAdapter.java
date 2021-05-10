package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MealModel;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.SandwichViewHolder> {

    private Context mContext;
    private List<MealModel> meals;

    public MealAdapter(Context mContext, List<MealModel> meals) {
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
        MealModel mealModel = meals.get(position);
        holder.txtMealName.setText(mealModel.getArName());
        if (mealModel.getPriceBySize() != null ||
                mealModel.getPriceByKilogram() != null ||
                mealModel.getPriceByBreadTypes() != null ||
                mealModel.getPriceByPiece() != null) {
            holder.txtFrechPrice.setText(R.string.price_by_selection);
        }else {
            holder.txtFrechPrice.setText(mealModel.getPriceByOne().getPrice()+" EGP");
        }
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class SandwichViewHolder extends RecyclerView.ViewHolder{
        TextView txtMealName, txtFrechPrice;
        public SandwichViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_snadwich_name);
            txtFrechPrice = itemView.findViewById(R.id.txt_french_price);
        }
    }
}
