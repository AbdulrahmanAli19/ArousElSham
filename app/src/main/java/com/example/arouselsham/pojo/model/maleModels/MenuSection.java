package com.example.arouselsham.pojo.model.maleModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MenuSection implements Serializable {

    private Double menuVersion;

    @SerializedName("tagsMap")
    private List<Map<String, String>> tagsMap;

    public MenuSection() {
    }

    public MenuSection(Double menuVersion, List<Map<String, String>> tagsMap) {
        this.menuVersion = menuVersion;
        this.tagsMap = tagsMap;
    }

    public Double getMenuVersion() {
        return menuVersion;
    }

    public void setMenuVersion(Double menuVersion) {
        this.menuVersion = menuVersion;
    }

    public List<Map<String, String>> getTagsMap() {
        return tagsMap;
    }

    public void setTagsMap(List<Map<String, String>> tagsMap) {
        this.tagsMap = tagsMap;
    }
}
