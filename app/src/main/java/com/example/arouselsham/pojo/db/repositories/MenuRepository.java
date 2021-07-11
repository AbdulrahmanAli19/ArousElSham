package com.example.arouselsham.pojo.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.db.dao.MenuDao;
import com.example.arouselsham.pojo.db.entities.Menu;

import java.util.List;

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

    public LiveData<List<Menu>> getAllMeals() {
        return allMeals;
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

        private DeleteAllMenuAsyncTask (MenuDao menuDao) {
            this.menuDao = menuDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            menuDao.deleteAll();
            return null;
        }
    }

}
