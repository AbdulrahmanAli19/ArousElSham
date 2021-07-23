package com.example.arouselsham.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.arouselsham.pojo.db.entities.Favorite;
import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.repositories.FavoriteRepository;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.example.arouselsham.pojo.model.maleModels.Meal;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FavoriteViewModel extends AndroidViewModel {

    private Meal meal = new Meal();

    private FavoriteRepository favoriteRepository;
    private MenuRepository menuRepository;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        menuRepository = new MenuRepository(application);
    }

    public LiveData<List<Favorite>> getFavorites() {
        return favoriteRepository.getGetAll();
    }

    public Menu getMealById(String id) throws ExecutionException, InterruptedException {
       return menuRepository.getMealById(id);
    }


}