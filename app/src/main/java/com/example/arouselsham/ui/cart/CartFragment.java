package com.example.arouselsham.ui.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.CartFragmentBinding;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = CartFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


}