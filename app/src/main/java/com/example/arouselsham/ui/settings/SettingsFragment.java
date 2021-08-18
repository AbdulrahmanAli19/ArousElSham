package com.example.arouselsham.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.SettingsFragmentBinding;

public class SettingsFragment extends Fragment {
    private SettingsFragmentBinding binding;
    private SettingsViewModel settingsViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.txtSelectedLanguage.setText(R.string.english);

        return root;
    }


}