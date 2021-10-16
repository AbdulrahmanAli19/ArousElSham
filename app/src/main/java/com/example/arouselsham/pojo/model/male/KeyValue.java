package com.example.arouselsham.pojo.model.male;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class KeyValue {

    private List<String> key;
    private List<Double> value;

    public KeyValue(Set<String> key, Collection<Double> value) {
        this.key = new ArrayList<String>(key);
        this.value = new ArrayList<Double>(value);
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }
}
