package com.example.arouselsham.ui.savedAddresses;

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

import com.example.arouselsham.databinding.SavedAddressesFragmentBinding;

public class SavedAddressesFragment extends Fragment {

    private SavedAddressesViewModel mViewModel;
    private NavController navController;
    private SavedAddressesFragmentBinding binding;

    public static SavedAddressesFragment newInstance() {
        return new SavedAddressesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SavedAddressesViewModel.class);
        binding = SavedAddressesFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }


}