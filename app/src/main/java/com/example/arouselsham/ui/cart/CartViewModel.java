package com.example.arouselsham.ui.cart;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.entities.Cart;
import com.example.arouselsham.pojo.db.repositories.CartRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepository repository;

    public CartViewModel(Application application) {
        super(application);
        repository = new CartRepository(application);
    }

    public LiveData<List<Cart>> getCartList() {
        return repository.getAllCarts();
    }

    public void removeOneCart (Cart cart) {
        repository.delete(cart);
    }

    public void updateCart(Cart cart){
        repository.update(cart);
    }
}