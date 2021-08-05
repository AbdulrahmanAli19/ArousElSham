package com.example.arouselsham.pojo.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.db.dao.FavoriteDao;
import com.example.arouselsham.pojo.db.entities.Favorite;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> getAll;

    public FavoriteRepository(Application application) {
        DataBaseManger dataBaseManger = DataBaseManger.getInstance(application);
        this.favoriteDao = dataBaseManger.favoriteDao();
        this.getAll = favoriteDao.selectAll();
    }

    public void insert(Favorite favorite) {
        new InsertAsyncTask(favoriteDao).execute(favorite);
    }

    public void delete(Favorite favorite) {
        new DeleteAsyncTask(favoriteDao).execute(favorite);
    }

    public LiveData<List<Favorite>> getGetAll() {
        return getAll;
    }

    private static class InsertAsyncTask extends AsyncTask<Favorite, Void, Void> {

        private FavoriteDao favoriteDao;

        public InsertAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.insert(favorites[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Favorite, Void, Void> {

        private FavoriteDao favoriteDao;

        public DeleteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            favoriteDao.delete(favorites[0]);
            return null;
        }
    }

}
