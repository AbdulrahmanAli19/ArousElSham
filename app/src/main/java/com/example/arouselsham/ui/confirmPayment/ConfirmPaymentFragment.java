package com.example.arouselsham.ui.confirmPayment;

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

import com.example.arouselsham.databinding.ConfirmPaymentFragmentBinding;

public class ConfirmPaymentFragment extends Fragment {

    private ConfirmPaymentFragmentBinding binding;

    private NavController navController;

    private ConfirmPaymentViewModel confirmPaymentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ConfirmPaymentFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        confirmPaymentViewModel = new ViewModelProvider(this).get(ConfirmPaymentViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}