package com.example.arouselsham.pojo.firebase;

import static com.example.arouselsham.pojo.Common.CUSTOMER_INFO_REFERENCE;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.model.CustomerInfoModel;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;
import com.example.arouselsham.ui.user.FirebaseInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseRepo {

    private static final String TAG = "MenuSectionsRepo";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReference(CUSTOMER_INFO_REFERENCE);
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private static FirebaseRepo instance;

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static FirebaseRepo getInstance() {

        if (instance == null) {
            instance = new FirebaseRepo();
        }

        return instance;
    }

    public LiveData<List<MenuSection>> menuTags() {
        return new FirestoreLiveData<>(firebaseFirestore.collection("MainManu"),
                MenuSection.class);
    }

    public LiveData<List<Meal>> getAllMeals(String section) {

        return new FirestoreLiveData<>(firebaseFirestore.collection("Menu")
                .document(section)
                .collection("MenuItems"),
                Meal.class);
    }

    public void getUserInfo(FirebaseInterface interface1) {
        FirebaseInterface firebaseInterface = interface1;

        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    CustomerInfoModel customer = snapshot.getValue(CustomerInfoModel.class);
                    firebaseInterface.onDataReceived(customer);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: " + error.getMessage());
            }
        });

    }


}