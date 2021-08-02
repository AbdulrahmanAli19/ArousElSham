package com.example.arouselsham.ui.addAddress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.arouselsham.databinding.AddAddressFragmentBinding;

public class AddAddressFragment extends Fragment {
    private NavController navController;

    private AddAddressViewModel addAddressViewModel;

    private AddAddressFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddAddressFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        addAddressViewModel = new ViewModelProvider(this).get(AddAddressViewModel.class);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}