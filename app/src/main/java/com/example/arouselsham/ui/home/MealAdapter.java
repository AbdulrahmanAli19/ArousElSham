package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MealModel;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private Context mContext;
    private List<MealModel> meals;

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
    public void onBindViewHolder(@NonNull MealViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(meals.get(position).getPriceByOne() != null)
            holder.txtMealPrice.setText(meals.get(position).getPriceByOne().getPrice() + " EGP");
        else if (meals.get(position).getPriceByBreadTypes() != null)
            holder.txtMealPrice.setText(meals.get(position).getPriceByBreadTypes().getFrenchPrice()+" EGP");
        else if (meals.get(position).getPriceByPiece() != null)
            holder.txtMealPrice.setText(meals.get(position).getPriceByPiece().getHalfChicken()+" EGP");
        else if (meals.get(position).getPriceByKilogram() != null)
            holder.txtMealPrice.setText(meals.get(position).getPriceByKilogram().getKilograms()+" EGP");
        else if (meals.get(position).getPriceBySize() != null)
            holder.txtMealPrice.setText(meals.get(position).getPriceBySize().getBigSizePrice()+" EGP");

        holder.txtMealName.setText(meals.get(position).getArName());

        holder.likeButton.setOnClickListener(v -> {
            meals.get(position).setLiked(!meals.get(position).isLiked());
            notifyItemChanged(position);
        });

        if (meals.get(position).isLiked()) {
            holder.likeButton.setImageResource(R.drawable.heart_on_b);

        } else {
            holder.likeButton.setImageResource(R.drawable.heart_off_b);
        }

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMealName, txtMealPrice;

        private ImageView likeButton;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_meal_name);
            txtMealPrice = itemView.findViewById(R.id.txt_meal_price);
            likeButton = itemView.findViewById(R.id.like_btn);
        }
    }
}
