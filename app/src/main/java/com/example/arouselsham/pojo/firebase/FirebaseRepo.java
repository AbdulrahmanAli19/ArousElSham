package com.example.arouselsham.pojo.firebase;

import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.model.maleModels.MenuSection;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseRepo {

    private static final String TAG = "MenuSectionsRepo";

    private static FirebaseRepo instance;

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static FirebaseRepo getInstance() {

        if (instance == null) {
            instance = new FirebaseRepo();
        }

        return instance;
    }

    public LiveData<List<MenuSection>> productListening() {
        return new FirestoreLiveData<>(firebaseFirestore.collection("MainManu"),
                MenuSection.class);
    }

}