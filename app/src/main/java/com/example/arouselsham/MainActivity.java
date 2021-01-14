package com.example.arouselsham;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.CustomerInfoModel;
import com.example.arouselsham.ui.auth.AuthFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DatabaseReference customerInfoRef;
    private FirebaseAuth.AuthStateListener listener;
    private FirebaseAuth auth;

    @BindView(R.id.auth_frame)
    FrameLayout frameLayout;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    @Override
    protected void onStart() {
        super.onStart();
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init() {

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
            }else {
                showAuthScreen();
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
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }

    private void showAuthScreen() {
        frameLayout.setVisibility(View.VISIBLE);
        navView.setVisibility(View.GONE);
        getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.auth_frame, new AuthFragment())
                .commit();
    }

    private void showHomeScreen() {
        getSupportActionBar().show();
        frameLayout.setVisibility(View.GONE);
        navView.setVisibility(View.VISIBLE);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_offers, R.id.navigation_orders, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}