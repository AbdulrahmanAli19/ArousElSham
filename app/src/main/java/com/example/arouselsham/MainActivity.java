package com.example.arouselsham;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.arouselsham.databinding.ActivityMainBinding;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.CustomerInfoModel;
import com.example.arouselsham.ui.home.HomeFragment;
import com.example.arouselsham.ui.user.SigningOutListener;
import com.firebase.ui.auth.AuthMethodPickerLayout;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeFragment.SetViewV, SigningOutListener {
    private static final String TAG = "MainActivity";
    private final static int LOGIN_REQUEST_CODE = 711;

    private List<AuthUI.IdpConfig> providers;
    private FirebaseDatabase database;
    private DatabaseReference customerInfoRef;
    private FirebaseAuth.AuthStateListener listener;
    private FirebaseAuth auth;
    private NavController navController;

    private ActivityMainBinding binding;

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (auth != null && listener != null)
            auth.removeAuthStateListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ArousElSham);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        init();

    }

    private void init() {

        binding.navView.setItemIconTintList(null);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        customerInfoRef = database.getReference(Common.CUSTOMER_INFO_REFERENCE);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
        customerInfoRef = FirebaseDatabase.getInstance().getReference(Common.CUSTOMER_INFO_REFERENCE);
        listener = myFirebase -> {
            FirebaseUser user = myFirebase.getCurrentUser();
            if (user != null) {
                FirebaseMessaging.getInstance().getToken()
                        .addOnSuccessListener(s -> {
                            Log.d(TAG, "init: onSuccess " + s);
                            ///TODO: update token
                        }).addOnFailureListener(e -> {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
                checkUserFromDatabase();
            } else {
                showLoginScreen();
            }
        };

    }

    private void checkUserFromDatabase() {
        customerInfoRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            CustomerInfoModel customerInfoModel = snapshot.getValue(CustomerInfoModel.class);
                            showHomeScreen();
                        } else {
                            showRegisterLayout();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Log.d(TAG, "onCancelled: " + error.getMessage());
                        Snackbar.make(findViewById(android.R.id.content),
                                error.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private void showRegisterLayout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);

        View view = LayoutInflater.from(this).inflate(R.layout.register_layout, null);
        TextInputLayout firstNameInput = view.findViewById(R.id.edt_firts_name);
        TextInputLayout lastNameInput = view.findViewById(R.id.edt_last_name);
        TextInputLayout phoneNumberInput = view.findViewById(R.id.edt_phone_number);
        Button btnSave = view.findViewById(R.id.btn_continue);

        if (FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber() != null
                && !TextUtils.isEmpty(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()))
            phoneNumberInput.getEditText()
                    .setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        builder.setView(view);
        AlertDialog dialog = builder.create();

        dialog.show();

        btnSave.setOnClickListener(v -> {
            if (TextUtils.isEmpty(firstNameInput.getEditText().getText().toString())) {
                firstNameInput.setError("First name can't be empty");
                return;
            } else if (TextUtils.isEmpty(lastNameInput.getEditText().getText().toString())) {
                lastNameInput.setError("Last name can't be empty");
                return;
            } else if (TextUtils.isEmpty(phoneNumberInput.getEditText().getText().toString())) {
                phoneNumberInput.setError("Phone number can't be empty");
                return;
            } else {
                CustomerInfoModel customer = new CustomerInfoModel();
                customer.setFirstName(firstNameInput.getEditText().getText().toString());
                customer.setLastName(lastNameInput.getEditText().getText().toString());
                customer.setPhoneNumber(phoneNumberInput.getEditText().getText().toString());
                customerInfoRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(customer)
                        .addOnSuccessListener(aVoid -> {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                    .Builder()
                                    .setDisplayName(customer.getFirstName())
                                    .build();
                            auth.getCurrentUser().updateProfile(profileUpdates);
                            Snackbar.make(findViewById(android.R.id.content), "Registered Successfully",
                                    Snackbar.LENGTH_SHORT).show();
                            dialog.dismiss();
                            showHomeScreen();
                            binding.splashLayout.setVisibility(View.GONE);
                            binding.homeLayout.setVisibility(View.VISIBLE);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "ERROR: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        });

            }
        });


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            } else {
                Snackbar.make(findViewById(android.R.id.content),
                        "ERROR: " + idpResponse.getError().getMessage(), Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void showHomeScreen() {
        binding.splashLayout.setVisibility(View.GONE);
        binding.homeLayout.setVisibility(View.VISIBLE);
        getSupportActionBar().show();
        getSupportActionBar().setElevation(0);
        binding.navView.setVisibility(View.VISIBLE);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_offers, R.id.navigation_orders, R.id.navigation_user)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public void onClick() {
        if (binding.navView.getVisibility() == View.VISIBLE) {
            binding.navView.setVisibility(View.GONE);

        } else {
            binding.navView.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onSignOut() {
        init();

    }
}