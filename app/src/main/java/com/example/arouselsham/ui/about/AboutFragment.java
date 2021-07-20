package com.example.arouselsham.ui.about;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AboutFragmentBinding;

public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;
    private AboutFragmentBinding binding;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AboutFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().onBackPressed();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
        return root;
    }



}