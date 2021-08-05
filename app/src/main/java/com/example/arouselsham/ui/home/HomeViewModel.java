package com.example.arouselsham.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.entities.MenuTags;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.example.arouselsham.pojo.db.repositories.MenuTagsRepository;
import com.example.arouselsham.pojo.firebase.FirebaseRepo;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private static final String TAG = "HomeViewModel";

    private final LiveData<List<Menu>> allMeals;
    private final LiveData<List<MenuTags>> allMenuTags;

    private final MenuRepository repository;
    private final MenuTagsRepository menuTagsRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MenuRepository(application);
        menuTagsRepository = new MenuTagsRepository(application);
        allMeals = repository.getAllMeals();
        allMenuTags = menuTagsRepository.getAllTags();
    }

    public LiveData<List<MenuSection>> getMenuTagsFromFirestore() {
        return FirebaseRepo.getInstance().menuTags();
    }

    public LiveData<List<Meal>> getMealsFromFirestore(String section) {
        return FirebaseRepo.getInstance().getAllMeals(section);
    }

    public void insertMeal(Menu menu) {
        repository.insert(menu);
    }

    public void deleteMeal(Menu menu) {
        repository.delete(menu);
    }

    public void updateMeal(Menu menu) {
        repository.update(menu);
    }

    public void deleteAllMeals() {
        repository.deleteAll();
    }

    public LiveData<List<Menu>> getAllMeals() {
        return allMeals;
    }

    public void insertMenuTags(MenuTags menu) {
        menuTagsRepository.insert(menu);
    }

    public void deleteMenuTags(MenuTags menu) {
        menuTagsRepository.delete(menu);
    }

    public void updateMenuTags(MenuTags menu) {
        menuTagsRepository.update(menu);
    }

    public void deleteAllMenuTags() {
        menuTagsRepository.deleteAll();
    }

    public LiveData<List<MenuTags>> getAllMenuTags() {
        return allMenuTags;
    }
}