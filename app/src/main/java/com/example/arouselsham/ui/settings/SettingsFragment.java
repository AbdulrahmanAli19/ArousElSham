package com.example.arouselsham.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.SettingsFragmentBinding;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private SettingsFragmentBinding binding;
    private SettingsViewModel settingsViewModel;
    private NavController navController;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.txtSelectedLanguage.setText(R.string.english);
        binding.accountInfo.setOnClickListener(this);
        binding.savedAddresses.setOnClickListener(this);
        binding.changeEmail.setOnClickListener(this);
        binding.changePassword.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        NavDirections navDirections;
        switch (v.getId()) {
            case R.id.accountInfo:
                navDirections = SettingsFragmentDirections.actionNavigationSettingsToAccountInfoFragment();
                navController.navigate(navDirections);
                break;
            case R.id.changeEmail:
                navDirections = SettingsFragmentDirections.actionNavigationSettingsToChangeEmailFragment();
                navController.navigate(navDirections);
                break;
            case R.id.changePassword:
                navDirections = SettingsFragmentDirections.actionNavigationSettingsToChangePasswordFragment();
                navController.navigate(navDirections);
                break;
            case R.id.savedAddresses:
                navDirections = SettingsFragmentDirections.actionNavigationSettingsToSavedAddressesFragment();
                navController.navigate(navDirections);
                break;


        }
    }
}