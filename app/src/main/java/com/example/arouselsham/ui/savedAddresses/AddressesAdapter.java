package com.example.arouselsham.ui.savedAddresses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AddressItemBinding;
import com.example.arouselsham.pojo.model.UserAddress;

import java.util.ArrayList;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.AddressesViewHolder> {

    private ArrayList<UserAddress> addresses;
    private final OnItemClickListener onItemClickListener;
    private final Context context;

    public AddressesAdapter(Context context,OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAddresses(ArrayList<UserAddress> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    public ArrayList<UserAddress> getAddresses() {
        return addresses;
    }

    @NonNull
    @Override
    public AddressesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AddressItemBinding binding = AddressItemBinding.inflate(inflater);
        return new AddressesViewHolder(binding, onItemClickListener);
    }

    public void deleteItemOn(int pos) {
        addresses.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesViewHolder holder, int position) {
        holder.binding.setAddress(addresses.get(position));
        if (selectedCard == position) {
            holder.binding.card.setCardBackgroundColor(context.getColor(R.color.gold));
        }else {
            holder.binding.card.setCardBackgroundColor(context.getColor(R.color.white));
        }
    }

    private static int selectedCard = 0;

    public void selectCardOn(int pos) {
        selectedCard = pos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (addresses != null)
            return addresses.size();
        else return 0;
    }

    static class AddressesViewHolder extends RecyclerView.ViewHolder {
        private final AddressItemBinding binding;

        public AddressesViewHolder(AddressItemBinding binding, OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.btnEdit.setOnClickListener(v -> onItemClickListener.onEditBtnClick(getBindingAdapterPosition()));
            binding.btnDelete.setOnClickListener(v -> onItemClickListener.onDeleteBtnClick(getBindingAdapterPosition()));
            binding.card.setOnClickListener(v -> onItemClickListener.onCardClick(getBindingAdapterPosition()));
        }

    }

    public interface OnItemClickListener {
        void onEditBtnClick(int pos);

        void onDeleteBtnClick(int pos);

        void onCardClick(int pos);
    }
}
