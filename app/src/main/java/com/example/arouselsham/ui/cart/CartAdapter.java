package com.example.arouselsham.ui.cart;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.CartRecyclerLayoutBinding;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.db.entities.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private static final String TAG = "CartAdapter";

    private CartViewHolder cartViewHolder;

    private final OnItemClickListener onItemClickListener;

    private List<Cart> carts = new ArrayList<>();

    public CartAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCart(List<Cart> carts) {
        this.carts.clear();
        this.carts = carts;
        notifyDataSetChanged();
    }

    public void removeCartAt(int position) {
        notifyItemRemoved(position);
        carts.remove(position);
    }

    public Cart getCurrentCart(int position) {
        return carts.get(position);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartRecyclerLayoutBinding binding = CartRecyclerLayoutBinding.inflate(inflater);
        return new CartViewHolder(binding, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Cart currentCart = carts.get(position);
        holder.binding.setCart(currentCart);
        holder.binding.executePendingBindings();

        if (holder.binding.getCart().getSelectedItem() == null) {
            holder.binding.selectionType.setVisibility(View.INVISIBLE);
            holder.binding.selectionType.setHeight(0);
        }

        Log.d(TAG, "onBindViewHolder: position= " + position);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (position == 0) {
            layoutParams.setMargins(0, 200, 0, 0);
            holder.binding.parent.setLayoutParams(layoutParams);
        } else if (position == carts.size() - 1) {
            layoutParams.setMargins(0, 0, 0, 200);
            holder.binding.parent.setLayoutParams(layoutParams);
        }

        cartViewHolder = holder;
    }

    public void updateCart(Cart cart, int position) {
        carts.remove(position);
        carts.add(position, cart);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CartRecyclerLayoutBinding binding;
        private final OnItemClickListener onItemClickListener;

        public CartViewHolder(CartRecyclerLayoutBinding binding, OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.onItemClickListener = onItemClickListener;
            this.binding = binding;
            binding.parent.setOnClickListener(this);
            binding.txtClose.setOnClickListener(this);
            binding.txtPlus.setOnClickListener(this);
            binding.txtMinus.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.parent:
                    onItemClickListener.onItemClick(getBindingAdapterPosition());
                    break;
                case R.id.txtClose:
                    onItemClickListener.onRemoveItem(getBindingAdapterPosition());
                    break;
                case R.id.txtPlus:
                    onItemClickListener.onPlus(getBindingAdapterPosition());
                    break;
                case R.id.txtMinus:
                    onItemClickListener.onMinus(getBindingAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onRemoveItem(int position);

        void onPlus(int position);

        void onMinus(int position);
    }
}
