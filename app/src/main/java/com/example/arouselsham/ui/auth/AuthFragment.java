package com.example.arouselsham.ui.auth;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AuthFragmentBinding;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;
import java.util.List;

public class AuthFragment extends Fragment {
    private final static int LOGIN_REQUEST_CODE = 711;

    private AuthViewModel authViewModel;
    private AuthFragmentBinding binding;
    private List<AuthUI.IdpConfig> providers;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        showLoginScreen();

        return root;
    }


    private void showLoginScreen() {
        AuthMethodPickerLayout pickerLayout = new AuthMethodPickerLayout
                .Builder(R.layout.auth_layout)
                .setGoogleButtonId(R.id.btn_sign_in_google)
                .setPhoneButtonId(R.id.btn_sign_in_phone)
                .build();

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAuthMethodPickerLayout(pickerLayout)
                .setIsSmartLockEnabled(false)
                .setTheme(R.style.LoginScreen)
                .setAvailableProviders(providers)
                .build(), LOGIN_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            } else {
                Toast.makeText(getContext(), "ERROR: " + idpResponse.getError().getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        }
    }
}