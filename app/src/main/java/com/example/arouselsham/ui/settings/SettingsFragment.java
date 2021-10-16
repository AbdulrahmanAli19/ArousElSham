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
import com.firebase.ui.auth.AuthUI;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private SettingsFragmentBinding binding;
    private SettingsViewModel settingsViewModel;
    private NavController navController;

    private SigningOutListener listener;

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
        binding.logout.setOnClickListener(this);
        listener = (SigningOutListener) getActivity();

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
        int id = v.getId();
        if (id == R.id.accountInfo) {
            navDirections = SettingsFragmentDirections.actionNavigationSettingsToAccountInfoFragment();
            navController.navigate(navDirections);
        } else if (id == R.id.savedAddresses) {
            navDirections = SettingsFragmentDirections.actionNavigationSettingsToSavedAddressesFragment(false);
            navController.navigate(navDirections);
        } else if (id == R.id.logout) {
            listener.onSignOut();
            AuthUI.getInstance().signOut(getContext());
        }
    }
}