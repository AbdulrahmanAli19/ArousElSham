package com.example.arouselsham.pojo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.arouselsham.pojo.db.entities.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite meal);

    @Delete
    void delete(Favorite meal);

    @Query("DELETE FROM favorite_table WHERE firebaseID = :id")
    void deleteById(String id);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<Favorite>> selectAll();

}
