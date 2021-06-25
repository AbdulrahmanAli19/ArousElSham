package com.example.arouselsham.pojo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite meal);

    @Delete
    void delete(Favorite meal);

    @Update
    void update(Favorite meal);

    @Query("SELECT * FROM favorite_table")
    List<Favorite> selectAll();

}
