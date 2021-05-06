package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class MenuTags implements Serializable {

    private String enName;
    private String arName;

    public MenuTags(String enName, String arName) {
        this.enName = enName;
        this.arName = arName;
    }

    public MenuTags() {
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
