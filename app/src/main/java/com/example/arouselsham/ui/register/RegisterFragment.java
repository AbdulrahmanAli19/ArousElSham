package com.example.arouselsham.ui.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.RegisterFragmentBinding;
import com.example.arouselsham.pojo.model.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

public class RegisterFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private RegisterFragmentBinding binding;

    private Register register;

    private final FirebaseAuth auth;
    private final DatabaseReference customerInfoRef;

    public RegisterFragment(FirebaseAuth auth, DatabaseReference customerInfoRef) {
        this.auth = auth;
        this.customerInfoRef = customerInfoRef;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        register = (Register) getActivity();

        binding.btnContinue.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.edtFirtsName.getEditText().getText().toString())) {
                binding.edtFirtsName.setError(getString(R.string.f_name_null));
            } else if (TextUtils.isEmpty(binding.edtLastName.getEditText().getText().toString())) {
                binding.edtLastName.setError(getString(R.string.l_name_null));
            } else if (TextUtils.isEmpty(binding.edtPhoneNumber.getEditText().getText().toString())) {
                binding.edtPhoneNumber.setError(getString(R.string.phone_number_null));

            } else if (TextUtils.isEmpty(binding.edtEmail.getEditText().getText().toString())) {
                binding.edtEmail.setError(getString(R.string.email_null));

            } else {
                Customer customer = new Customer(
                        binding.edtFirtsName.getEditText().getText().toString(),
                        binding.edtLastName.getEditText().getText().toString(),
                        binding.edtEmail.getEditText().getText().toString(),
                        binding.edtPhoneNumber.getEditText().getText().toString());

                customerInfoRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(customer)
                        .addOnSuccessListener(aVoid -> {

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                    .Builder()
                                    .setDisplayName(customer.getFirstName())
                                    .build();
                            auth.getCurrentUser().updateProfile(profileUpdates);
                            Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();


                            register.onRegisterSuccess();

                        }).addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "ERROR: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });

            }
        });

        return root;
    }


}