package com.example.arouselsham.ui.home;

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

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.SandwichViewHolder> {

    private Context mContext;
    private List<MealModel> meals;

    public SandwichAdapter(Context mContext, List<MealModel> meals) {
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

    @Override
    public void onBindViewHolder(@NonNull SandwichViewHolder holder, int position) {
        MealModel model = meals.get(position);
        holder.txtMealName.setText(model.getArName());
        holder.txtSaro5Price.setText(model.getPriceByBreadTypes().getSaro5Price()+" EGP");
        holder.txtSoriPrice.setText(model.getPriceByBreadTypes().getSyrianPrice()+",");
        holder.txtFrechPrice.setText(model.getPriceByBreadTypes().getFrenchPrice()+",");
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class SandwichViewHolder extends RecyclerView.ViewHolder{
        TextView txtMealName, txtSoriPrice, txtSaro5Price, txtFrechPrice;
        public SandwichViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMealName = itemView.findViewById(R.id.txt_snadwich_name);
            txtSoriPrice = itemView.findViewById(R.id.txt_sori_price);
            txtSaro5Price = itemView.findViewById(R.id.txt_saro5_price);
            txtFrechPrice = itemView.findViewById(R.id.txt_french_price);
        }
    }
}
