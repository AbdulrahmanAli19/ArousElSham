package com.example.arouselsham.pojo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuDao {

    @Insert
    void insert (Menu menu);

    @Delete
    void delete(Menu menu);

    @Update
    void update(Menu menu);

    @Query("SELECT * FROM menu_table")
    List<Menu> selectAll();


}
