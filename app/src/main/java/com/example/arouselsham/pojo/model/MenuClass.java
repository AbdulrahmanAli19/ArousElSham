package com.example.arouselsham.pojo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuClass {
    @SerializedName("arTags")
    private List<String> arTags;
    @SerializedName("enTags")
    private List<String> enTags;

    public List<String> getArTags() {
        return arTags;
    }

    public void setArTags(List<String> arTags) {
        this.arTags = arTags;
    }

    public List<String> getEnTags() {
        return enTags;
    }

    public void setEnTags(List<String> enTags) {
        this.enTags = enTags;
    }
}
