package com.example.arouselsham.services;

import android.app.Application;
import android.content.Context;

import com.example.arouselsham.pojo.db.entities.Menu;
import com.example.arouselsham.pojo.db.entities.MenuTags;
import com.example.arouselsham.pojo.db.repositories.MenuRepository;
import com.example.arouselsham.pojo.db.repositories.MenuTagsRepository;

public class ServiceHelper {
    private Context context;
    private final MenuRepository repository;
    private final MenuTagsRepository menuTagsRepository;

    public ServiceHelper(Application context) {
        this.context = context;
        repository = new MenuRepository(context);
        menuTagsRepository = new MenuTagsRepository(context);
    }

    public void insertMenuTags(MenuTags menu) {
        menuTagsRepository.insert(menu);
    }

    public void deleteAllMenuTags() {
        menuTagsRepository.deleteAll();
    }

    public void deleteAllMeals() {
        repository.deleteAll();
    }

    public void insertMeal(Menu menu) {
        repository.insert(menu);
    }


}
