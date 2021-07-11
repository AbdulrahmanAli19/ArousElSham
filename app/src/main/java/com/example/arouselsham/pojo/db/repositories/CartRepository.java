package com.example.arouselsham.pojo.db.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.arouselsham.pojo.db.DataBaseManger;
import com.example.arouselsham.pojo.db.dao.CartDao;
import com.example.arouselsham.pojo.db.entities.Cart;

import java.util.List;

public class CartRepository {

    private CartDao cartDao;
    private LiveData<List<Cart>> allCarts;

    public CartRepository (Application application) {
        DataBaseManger dataBaseManger = DataBaseManger.getInstance(application);
        this.cartDao = dataBaseManger.cartDao();
        this.allCarts = cartDao.selectAll();
    }

    public void insert (Cart cart) {
        new InsertCart(cartDao).execute(cart);
    }

    public void delete (Cart cart) {
        new DeleteCart(cartDao).execute(cart);
    }

    public LiveData<List<Cart>> getAllCarts () {
        return allCarts;
    }

    private static class InsertCart extends AsyncTask<Cart, Void, Void> {

        private CartDao cartDao;

        public InsertCart (CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDao.Insert(carts[0]);
            return null;
        }
    }

    private static class DeleteCart extends AsyncTask<Cart, Void, Void> {

        private CartDao cartDao;

        public DeleteCart (CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Cart... carts) {
            cartDao.delete(carts[0]);
            return null;
        }
    }

}
