package com.example.arouselsham.ui.auth;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.Common;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class AuthFragment extends Fragment implements View.OnClickListener {
    private List<AuthUI.IdpConfig> providers ;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;

    private FirebaseDatabase database;
    private DatabaseReference customerInfoRef;
    private AuthViewModel mViewModel;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (auth != null && listener != null)
            auth.removeAuthStateListener(listener);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_fragment, container, false);
        Button btnGoogle = view.findViewById(R.id.btn_sign_in_google);
        Button btnPhone = view.findViewById(R.id.btn_sign_in_phone);
        btnGoogle.setOnClickListener(this);
        btnPhone.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        customerInfoRef = database.getReference(Common.CUSTOMER_INFO_REFERENCE);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        // TODO: Use the ViewModel
        AuthMethodPickerLayout pickerLayout = new AuthMethodPickerLayout
                .Builder(R.layout.auth_fragment)
                .setGoogleButtonId(R.id.btn_sign_in_google)
                .setPhoneButtonId(R.id.btn_sign_in_phone)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in_google:

                break;

            case R.id.btn_sign_in_phone:

                break;
        }
    }

}