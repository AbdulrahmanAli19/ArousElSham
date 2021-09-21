package com.example.arouselsham.ui.accountInfo;

import androidx.lifecycle.ViewModel;

import com.example.arouselsham.pojo.firebase.FirebaseRepo;

public class AccountInfoViewModel extends ViewModel {

    public void getCurrentUser(FirebaseInterface firebaseInterface) {
        // TODO: Implement the ViewModel
        FirebaseRepo repo = FirebaseRepo.getInstance();
        repo.getUserInfo(firebaseInterface);
    }
}