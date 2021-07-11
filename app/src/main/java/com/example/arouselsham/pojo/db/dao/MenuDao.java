package com.example.arouselsham.pojo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arouselsham.pojo.db.entities.Menu;

import java.util.List;

@Dao
public interface MenuDao {

    @Insert
    void insert(Menu menu);

    @Delete
    void delete(Menu menu);

    @Update
    void update(Menu menu);

    @Query("DELETE FROM menu_table")
    void deleteAll();

    @Query("SELECT * FROM menu_table ORDER BY _PRICE ASC")
    LiveData<List<Menu>> getAllMeals();

    @Query("SELECT * FROM menu_table WHERE _ID = :firebaseId")
    Menu getMeal(String firebaseId);

}
