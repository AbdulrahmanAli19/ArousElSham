package com.example.arouselsham.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.arouselsham.pojo.db.entities.MenuTags;
import com.example.arouselsham.pojo.model.male.MenuSection;

public class SaveToDataBaseService extends IntentService {

    private static final String TAG = "SaveToDataBaseService";
    private MenuSection section;
    private ServiceHelper serviceHelper = new ServiceHelper(this.getApplication());

    public SaveToDataBaseService() {
        super(TAG);
        setIntentRedelivery(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service has been started.");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        section = (MenuSection) intent.getSerializableExtra("ex");
        saveMenuTagsToDatabase();

    }

/*
    private void saveMenuToDatabase() {
        serviceHelper.deleteAllMeals();
        for (int i = 0; i < section.getTagsMap().size(); i++) {

            homeViewModel.getMealsFromFirestore(section.getTagsMap().get(i).get("enName"))
                    .observe(getViewLifecycleOwner(), meals -> {

                        for (int j = 0; j < meals.size(); j++) {
                            Menu menu = new Menu(meals.get(j));
                            menu.setId(meals.get(j).getId());

                            homeViewModel.insertMeal(menu);
                        }

                    });
        }

    }
*/

    private void saveMenuTagsToDatabase() {
        serviceHelper.deleteAllMenuTags();
        for (int i = 0; i < section.getTagsMap().size(); i++) {
            String imageUrl = section.getTagsMap().get(i).get("imageUrl");
            String enName = section.getTagsMap().get(i).get("enName");
            String arName = section.getTagsMap().get(i).get("arName");
            MenuTags menuTags = new MenuTags(imageUrl, enName, arName);
            serviceHelper.insertMenuTags(menuTags);
        }
        Log.d(TAG, "Tags saved: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}