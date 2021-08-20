package com.example.arouselsham.ui.accountInfo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AccountInfoFragmentBinding;

public class AccountInfoFragment extends Fragment {

    private AccountInfoViewModel accountInfoViewModel;
    private AccountInfoFragmentBinding binding;
    private NavController navController;

    public static AccountInfoFragment newInstance() {
        return new AccountInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        accountInfoViewModel = new ViewModelProvider(this).get(AccountInfoViewModel.class);
        binding = AccountInfoFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}