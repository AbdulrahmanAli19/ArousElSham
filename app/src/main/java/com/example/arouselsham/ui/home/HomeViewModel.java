package com.example.arouselsham.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MenuRepository repository;

    private LiveData<List<Menu>> allMeals;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MenuRepository(application);
        allMeals = repository.getAllMeals();
    }

    public void insert (Menu menu){
        repository.insert(menu);
    }

    public void delete (Menu menu) {
        repository.delete(menu);
    }

    public void update (Menu menu){
        repository.update(menu);
    }

    public void deleteAll (){
        repository.deleteAll();
    }

    public LiveData<List<Menu>> getAllMeals (){
        return allMeals;
    }
}