package com.example.arouselsham.pojo.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_tags_table")
public class MenuTags {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String imageUrl;

    private String enName;

    private String arName;

    public MenuTags( String imageUrl, String enName, String arName) {
        this.imageUrl = imageUrl;
        this.enName = enName;
        this.arName = arName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }
}
