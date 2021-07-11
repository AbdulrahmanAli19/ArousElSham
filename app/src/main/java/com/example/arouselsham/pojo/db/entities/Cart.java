package com.example.arouselsham.pojo.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class Cart {

    @ColumnInfo(name = "_ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firebaseId;

    public Cart(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
}
