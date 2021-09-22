package com.example.arouselsham.ui;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.ActivityMainBinding;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.Customer;
import com.example.arouselsham.ui.auth.AuthFragment;
import com.example.arouselsham.ui.home.HomeFragment;
import com.example.arouselsham.ui.register.Register;
import com.example.arouselsham.ui.register.RegisterFragment;
import com.example.arouselsham.ui.settings.SigningOutListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements HomeFragment.SetViewV,
        SigningOutListener, Register {
    private static final String TAG = "MainActivity";

    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    private NavController navController;

    private DatabaseReference customerInfoRef;
    private FirebaseAuth.AuthStateListener listener;
    private FirebaseAuth auth;


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
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();
        init();

    }

    private void init() {

        binding.navView.setItemIconTintList(null);
        auth = FirebaseAuth.getInstance();
        customerInfoRef = FirebaseDatabase.getInstance().getReference(Common.CUSTOMER_INFO_REFERENCE);

        listener = myFirebase -> {
            FirebaseUser user = myFirebase.getCurrentUser();
            if (user != null) {
                FirebaseMessaging.getInstance().getToken()
                        .addOnSuccessListener(s -> {
                            Log.d(TAG, "init: onSuccess " + s);
                            ///TODO: update token

                        }).addOnFailureListener(e ->
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
                checkUserFromDatabase();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(binding.frame.getId(), new AuthFragment())
                        .commit();
            }
        };

    }

    private void checkUserFromDatabase() {
        customerInfoRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Customer customer = snapshot.getValue(Customer.class);
                            binding.frame.setVisibility(View.GONE);
                            showHomeScreen();
                        } else {
                            ///TODO: Show RegisterLayout
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(binding.frame.getId(),
                                            new RegisterFragment(auth, customerInfoRef))
                                    .commit();
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

    private void showHomeScreen() {
        binding.frame.setVisibility(View.GONE);
        binding.splashLayout.setVisibility(View.GONE);
        binding.homeLayout.setVisibility(View.VISIBLE);
        getSupportActionBar().show();
        getSupportActionBar().setElevation(0);
        binding.navView.setVisibility(View.VISIBLE);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_cart, R.id.navigation_order)
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

    @Override
    public void onRegisterSuccess() {
        showHomeScreen();
    }
}