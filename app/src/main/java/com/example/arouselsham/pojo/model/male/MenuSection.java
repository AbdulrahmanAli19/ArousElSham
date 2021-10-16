package com.example.arouselsham.pojo.model.male;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuSection implements Serializable {

    private Float menuVersion;

    @SerializedName("tagsMap")
    private List<Map<String, String>> tagsMap = new ArrayList<>();

    public MenuSection() {
    }

    public MenuSection(Float menuVersion, List<Map<String, String>> tagsMap) {
        this.menuVersion = menuVersion;
        this.tagsMap = tagsMap;
    }

    public Float getMenuVersion() {
        return menuVersion;
    }

    public void setMenuVersion(Float menuVersion) {
        this.menuVersion = menuVersion;
    }

    public List<Map<String, String>> getTagsMap() {
        return tagsMap;
    }

    public void setTagsMap(List<Map<String, String>> tagsMap) {
        this.tagsMap = tagsMap;
    }
}
