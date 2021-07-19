package com.example.arouselsham.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.example.arouselsham.pojo.firebase.FirebaseRepo;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private static final String TAG = "HomeViewModel";

    private final Application application;

    private final LiveData<List<Menu>> allMeals;

    private final MenuRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new MenuRepository(application);
        allMeals = repository.getAllMeals();
    }

    public LiveData<List<MenuSection>> getMenuTags() {
        return FirebaseRepo.getInstance().productListening();
    }

    public void insert(Menu menu) {
        repository.insert(menu);
    }

    public void delete(Menu menu) {
        repository.delete(menu);
    }

    public void update(Menu menu) {
        repository.update(menu);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Menu>> getAllMeals() {
        return allMeals;
    }
}