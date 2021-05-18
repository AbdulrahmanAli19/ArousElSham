package com.example.arouselsham.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;

import java.util.List;

public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingsViewHolder> {

    private Context mContext;
    private boolean isItArabic;
    private List<MenuTopping> toppings;

    public ToppingAdapter(Context mContext, List<MenuTopping> toppings, boolean isItArabic) {
        this.mContext = mContext;
        this.toppings = toppings;
        this.isItArabic = isItArabic;
    }

    @NonNull
    @Override
    public ToppingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.topping_layout, parent, false);
        return new ToppingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToppingsViewHolder holder, int position) {
        MenuTopping topping = toppings.get(position);
        holder.txtPrice.setText(topping.getToppingPrice() + " EGP");
        holder.txtName.setText(
                (isItArabic ? topping.getToppingArName() : topping.getToppingEnName()));

        holder.itemView.setOnClickListener(v -> {
            holder.checkBox.setChecked(!holder.checkBox.isChecked());
        });
    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    class ToppingsViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView txtName, txtPrice;

        public ToppingsViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.topping_checkBox);
            txtName = itemView.findViewById(R.id.txt_toping_name);
            txtPrice = itemView.findViewById(R.id.txt_topping_price);
        }
    }
}
