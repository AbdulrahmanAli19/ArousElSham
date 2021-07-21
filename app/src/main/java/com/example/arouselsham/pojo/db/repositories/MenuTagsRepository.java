package com.example.arouselsham.pojo.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.db.dao.MenuTagsDao;
import com.example.arouselsham.pojo.db.entities.MenuTags;

import java.util.List;

public class MenuTagsRepository {

    private MenuTagsDao dao;
    private LiveData<List<MenuTags>> allTags;

    public MenuTagsRepository(Application application) {
        DataBaseManger dataBaseManger = DataBaseManger.getInstance(application);
        this.dao = dataBaseManger.menuTagsDao();
        this.allTags = dao.getAll();
    }

    public void delete(MenuTags menuTags) {
        new DeleteMenuTagAsyncTask(dao).execute(menuTags);
    }

    public void insert(MenuTags menuTags) {
        new InsertMenuTagAsyncTask(dao).execute(menuTags);
    }

    public void update(MenuTags menuTags) {
        new UpdateMenuTagAsyncTask(dao).execute(menuTags);
    }

    public void deleteAll () {
        new DeleteAllMenuTagsAsyncTask(dao).execute();
    }

    public LiveData<List<MenuTags>> getAllTags() {
        return allTags;
    }

    private static class InsertMenuTagAsyncTask extends AsyncTask <MenuTags, Void, Void>{
        private MenuTagsDao menuTagsDao;

        public InsertMenuTagAsyncTask(MenuTagsDao menuTagsDao) {
            this.menuTagsDao = menuTagsDao;
        }

        @Override
        protected Void doInBackground(MenuTags... menuTags) {
            menuTagsDao.insert(menuTags[0]);
            return null;
        }
    }

    private static class DeleteMenuTagAsyncTask extends AsyncTask <MenuTags, Void, Void> {
        private MenuTagsDao menuTagsDao;

        public DeleteMenuTagAsyncTask(MenuTagsDao menuTagsDao) {
            this.menuTagsDao = menuTagsDao;
        }

        @Override
        protected Void doInBackground(MenuTags... menuTags) {
            menuTagsDao.delete(menuTags[0]);
            return null;
        }
    }

    private static class UpdateMenuTagAsyncTask extends AsyncTask <MenuTags, Void, Void>{
        private MenuTagsDao menuTagsDao;

        public UpdateMenuTagAsyncTask(MenuTagsDao menuTagsDao) {
            this.menuTagsDao = menuTagsDao;
        }

        @Override
        protected Void doInBackground(MenuTags... menuTags) {
            menuTagsDao.delete(menuTags[0]);
            return null;
        }
    }

    private static class DeleteAllMenuTagsAsyncTask extends AsyncTask<Void, Void, Void>{
        private MenuTagsDao menuTagsDao;

        public DeleteAllMenuTagsAsyncTask(MenuTagsDao menuTagsDao) {
            this.menuTagsDao = menuTagsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            menuTagsDao.deleteAll();
            return null;
        }
    }
}
