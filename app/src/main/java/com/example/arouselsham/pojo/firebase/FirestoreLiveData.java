package com.example.arouselsham.pojo.firebase;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirestoreLiveData<T> extends LiveData<T> {

    public static final String TAG = "debinf firestore";

    private ListenerRegistration registration;

    private final CollectionReference colRef;
    private final Class clazz;

    public FirestoreLiveData(CollectionReference colRef, Class clazz) {
        this.colRef = colRef;
        this.clazz = clazz;
    }


    EventListener<QuerySnapshot> eventListener = new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            if (e != null) {
                Log.i(TAG, "Listen failed.", e);
                return;
            }

            List<T> itemList = new ArrayList<>();
            if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                    T item = (T) snapshot.toObject(clazz);
                    itemList.add(item);
                    Log.i(TAG, "snapshot is " + snapshot.getId());
                }
                setValue((T) itemList);
            }
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        registration = colRef.addSnapshotListener(eventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasActiveObservers()) {
            registration.remove();
            registration = null;
        }
    }
}
