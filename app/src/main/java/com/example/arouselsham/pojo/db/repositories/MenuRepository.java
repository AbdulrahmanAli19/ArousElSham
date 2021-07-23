package com.example.arouselsham.pojo.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.db.dao.MenuDao;
import com.example.arouselsham.pojo.db.entities.Menu;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MenuRepository {

    private MenuDao menuDao;
    private LiveData<List<Menu>> allMeals;



    public MenuRepository(Application application) {
        DataBaseManger dataBaseManger = DataBaseManger.getInstance(application);
        this.menuDao = dataBaseManger.menuDao();
        this.allMeals = menuDao.getAllMeals();

    }

    public void insert(Menu menu) {
        new InsertMenuAsyncTask(menuDao).execute(menu);
    }

    public void delete(Menu menu) {
        new DeleteMenuAsyncTask(menuDao).execute(menu);
    }

    public void update(Menu menu) {
        new UpdateMenuAsyncTask(menuDao).execute(menu);
    }

    public void deleteAll() {
        new DeleteAllMenuAsyncTask(menuDao).execute();
    }

    public Menu getMealById(String id) throws ExecutionException, InterruptedException {
        return new GetMealByIdAsyncTask(menuDao, id).execute().get();
    }

    public LiveData<List<Menu>> getAllMeals() {
        return allMeals;
    }

    public LiveData<List<Menu>> getMealsBySection(String section) {
        return  menuDao.getMealBySection(section);
    }


    private static class GetMealByIdAsyncTask extends AsyncTask<Void, Void, Menu>{
        private MenuDao menuDao;
        private String id;

        public GetMealByIdAsyncTask(MenuDao menuDao, String id) {
            this.menuDao = menuDao;
            this.id = id;
        }

        @Override
        protected Menu doInBackground(Void... menus) {
            return menuDao.getMealByID(id);

        }
    }


    private static class InsertMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private InsertMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.insert(menus[0]);
            return null;
        }
    }

    private static class DeleteMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private DeleteMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.delete(menus[0]);
            return null;
        }
    }

    private static class UpdateMenuAsyncTask extends AsyncTask<Menu, Void, Void> {
        private MenuDao menuDao;

        private UpdateMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Menu... menus) {
            menuDao.update(menus[0]);
            return null;
        }
    }

    private static class DeleteAllMenuAsyncTask extends AsyncTask<Void, Void, Void> {
        private MenuDao menuDao;

        private DeleteAllMenuAsyncTask(MenuDao menuDao) {
            this.menuDao = menuDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            menuDao.deleteAll();
            return null;
        }
    }

}
