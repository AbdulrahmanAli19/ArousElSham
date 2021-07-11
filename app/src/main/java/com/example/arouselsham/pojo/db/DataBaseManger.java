package com.example.arouselsham.pojo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.arouselsham.pojo.db.dao.CartDao;
import com.example.arouselsham.pojo.db.dao.FavoriteDao;
import com.example.arouselsham.pojo.db.dao.MenuDao;
import com.example.arouselsham.pojo.db.entities.Cart;
import com.example.arouselsham.pojo.db.entities.Favorite;
import com.example.arouselsham.pojo.db.entities.Menu;


@Database(entities = {Cart.class, Menu.class, Favorite.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class DataBaseManger extends RoomDatabase {

    private static DataBaseManger instance;

    public abstract CartDao cartDao ();

    public abstract FavoriteDao favoriteDao();

    public abstract MenuDao menuDao();

    public static synchronized DataBaseManger getInstance(Context context) {
        if (instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(),
                            DataBaseManger.class,
                            "Menu DataBase")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
