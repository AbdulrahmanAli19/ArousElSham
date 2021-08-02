package com.example.arouselsham.ui.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;

import com.example.arouselsham.pojo.firebase.FirebaseRepo;

public class UserViewModel extends AndroidViewModel {
    private FirebaseRepo repo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repo = FirebaseRepo.getInstance();

    }

    public void getCurrentUser(FirebaseInterface firebaseInterface) {
        repo.getUserInfo(firebaseInterface);
    }
}