package com.example.arouselsham.pojo.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.arouselsham.pojo.model.maleModels.Meal;

@Entity(tableName = "favorite_table")
public class Favorite {

    @ColumnInfo(name = "_ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firebaseID;

    public Favorite() {
    }

    public Favorite(String firebaseID) {
        this.firebaseID = firebaseID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }
}
