package com.example.arouselsham.ui.accountInfo;

import com.example.arouselsham.pojo.model.Customer;
import com.google.firebase.database.DataSnapshot;

public interface FirebaseInterface {
    void onDataReceived(Customer customer, DataSnapshot snapshot);
}
