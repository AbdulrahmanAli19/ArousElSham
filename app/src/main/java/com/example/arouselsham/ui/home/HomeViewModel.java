package com.example.arouselsham.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public HomeViewModel() {
        FirebaseUser user = auth.getCurrentUser();
        mText = new MutableLiveData<>();
        if (user != null)
            mText.setValue("Good Evening! " + user.getDisplayName());
    }

    public LiveData<String> getText() {
        return mText;
    }
}