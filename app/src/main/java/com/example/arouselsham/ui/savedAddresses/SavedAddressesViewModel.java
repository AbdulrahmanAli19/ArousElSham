package com.example.arouselsham.ui.savedAddresses;

import androidx.lifecycle.ViewModel;

import com.example.arouselsham.pojo.firebase.FirebaseRepo;
import com.example.arouselsham.ui.accountInfo.FirebaseInterface;

public class SavedAddressesViewModel extends ViewModel {

    public void getCurrentUser(FirebaseInterface firebaseInterface) {
        FirebaseRepo repo = FirebaseRepo.getInstance();
        repo.getUserInfo(firebaseInterface);
    }
}