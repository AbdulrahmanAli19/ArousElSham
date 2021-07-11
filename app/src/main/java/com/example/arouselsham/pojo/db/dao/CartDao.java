package com.example.arouselsham.pojo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.arouselsham.pojo.db.entities.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void Insert(Cart cart/* an object of the class entity*/);

    @Delete
    void delete(Cart add);

    @Query("SELECT * FROM cart_table")
    LiveData<List<Cart>> selectAll();

}
