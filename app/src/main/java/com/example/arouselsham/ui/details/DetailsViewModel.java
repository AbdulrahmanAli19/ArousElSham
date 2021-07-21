package com.example.arouselsham.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.entities.Cart;
import com.example.arouselsham.pojo.db.entities.Favorite;
import com.example.arouselsham.pojo.db.repositories.CartRepository;
import com.example.arouselsham.pojo.db.repositories.FavoriteRepository;

import java.util.List;

public class DetailsViewModel extends AndroidViewModel {

    private final FavoriteRepository repository;
    private final CartRepository cartRepository;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        cartRepository = new CartRepository(application);
    }

    public void insertToFavorite(Favorite favorite) {
        repository.insert(favorite);
    }

    public void deleteFromFavorite(Favorite favorite) {
        repository.delete(favorite);
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return repository.getGetAll();
    }

    public void insertToCart(Cart cart) {
        cartRepository.insert(cart);
    }

}