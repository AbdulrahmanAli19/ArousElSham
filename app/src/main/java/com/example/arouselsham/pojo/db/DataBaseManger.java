package com.example.arouselsham.pojo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Cart.class, Menu.class, Favorite.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class DataBaseManger extends RoomDatabase {

    public abstract CartDao cartDao ();

    public abstract FavoriteDao favoriteDao();

    public abstract MenuDao menuDao();

}
