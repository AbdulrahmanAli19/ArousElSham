package com.example.arouselsham.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private MenuRepository repository;
    private Application application;

    private LiveData<List<Menu>> allMeals;
    private LiveData<MenuSection> allTags;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
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