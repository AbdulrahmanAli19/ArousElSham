package com.example.arouselsham.ui.section;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;

import java.util.List;


public class SectionViewModel extends AndroidViewModel {

    private MenuRepository repository;

    public SectionViewModel(@NonNull Application application) {
        super(application);
        repository = new MenuRepository(application);
    }

    public LiveData<List<Menu>> getMealsBySection(String section) {
        return repository.getMealsBySection(section);
    }


}