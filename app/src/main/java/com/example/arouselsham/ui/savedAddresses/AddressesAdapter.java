package com.example.arouselsham.ui.savedAddresses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.databinding.AddressItemBinding;
import com.example.arouselsham.pojo.model.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.AddressesViewHolder> {

    private ArrayList<UserAddress> addresses;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public AddressesAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

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
        LayoutInflater inflater = LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    static class AddressesViewHolder extends RecyclerView.ViewHolder {
        private final AddressItemBinding binding;

        public AddressesViewHolder(AddressItemBinding binding, OnItemClickListener onItemClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.card.setOnClickListener(v -> onItemClickListener.onItemClick(getBindingAdapterPosition()));
            binding.btnDelete.setOnClickListener(v -> onItemClickListener.onItemDeleted(getBindingAdapterPosition()));
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
        void onItemDeleted(int pos);
    }
}
