package com.example.arouselsham.ui.addAddress;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.arouselsham.pojo.firebase.FirebaseRepo;
import com.example.arouselsham.ui.accountInfo.FirebaseInterface;

public class AddAddressViewModel extends AndroidViewModel {
    private FirebaseRepo repo;

    public AddAddressViewModel(@NonNull Application application) {
        super(application);
        repo = FirebaseRepo.getInstance();
    }
    public void getCurrentUser(FirebaseInterface firebaseInterface) {
        repo.getUserInfo(firebaseInterface);
    }}