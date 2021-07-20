package com.example.arouselsham.pojo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arouselsham.pojo.db.entities.MenuTags;

import java.util.List;

@Dao
public interface MenuTagsDao {

    @Insert
    void insert(MenuTags menuTags);

    @Delete
    void delete(MenuTags menuTags);

    @Update
    void update(MenuTags menuTags);

    @Query("DELETE FROM menu_tags_table")
    void deleteAll();

    @Query("SELECT * FROM menu_tags_table")
    LiveData<List<MenuTags>> getAll();

}
