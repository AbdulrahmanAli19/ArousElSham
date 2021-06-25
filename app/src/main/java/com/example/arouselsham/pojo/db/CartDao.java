package com.example.arouselsham.pojo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void Insert(Cart cart/* an object of the class entity*/);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart add);

    @Query("SELECT * FROM cart_table")
    List<Cart> selectAll();

}
