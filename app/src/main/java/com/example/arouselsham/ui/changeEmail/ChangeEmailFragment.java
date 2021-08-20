package com.example.arouselsham.ui.changeEmail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arouselsham.databinding.ChangeEmailFragmentBinding;

public class ChangeEmailFragment extends Fragment {

    private ChangeEmailViewModel changeEmailViewModel;
    private NavController navController;
    private ChangeEmailFragmentBinding binding;


    public static ChangeEmailFragment newInstance() {
        return new ChangeEmailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        changeEmailViewModel = new ViewModelProvider(this).get(ChangeEmailViewModel.class);
        binding = ChangeEmailFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }



}