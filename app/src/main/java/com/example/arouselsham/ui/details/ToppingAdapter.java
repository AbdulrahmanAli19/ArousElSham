package com.example.arouselsham.ui.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingsViewHolder> {

    private final Context mContext;
    private final boolean isItArabic;
    private List<MenuTopping> toppings = new ArrayList<>();
    private final ItemClickListener itemClickListener;


    public ToppingAdapter(Context mContext, List<MenuTopping> toppings, boolean isItArabic,
                          ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.toppings = toppings;
        this.isItArabic = isItArabic;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ToppingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.topping_layout, parent, false);
        return new ToppingsViewHolder(view, itemClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ToppingsViewHolder holder, int position) {
        MenuTopping topping = toppings.get(position);
        holder.txtPrice.setText(topping.getToppingPrice() + " EGP");
        holder.txtName.setText(
                (isItArabic ? topping.getToppingArName() : topping.getToppingEnName()));


    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }

    public class ToppingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CheckBox checkBox;
        private final TextView txtName;
        private final TextView txtPrice;
        private final ItemClickListener itemClickListener;

        public ToppingsViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.topping_checkBox);
            txtName = itemView.findViewById(R.id.txt_toping_name);
            txtPrice = itemView.findViewById(R.id.txt_topping_price);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemCLickListener(getBindingAdapterPosition(),
                    toppings.get(getBindingAdapterPosition()).getToppingPrice(), checkBox, toppings.get(getBindingAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onItemCLickListener(int position, double price, CheckBox checkBox, MenuTopping menuTopping);
    }
}
