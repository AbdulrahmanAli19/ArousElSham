package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class PriceByKilogram implements Serializable {
    private Double kilograms;
    private Double halfKilograms;
    private Double quarterKilograms;
    private Double tomnKilograms;

    PriceByKilogram() {
    }

    public PriceByKilogram(Double kilograms, Double halfKilograms, Double quarterKilograms, Double tomnKilograms) {
        this.kilograms = kilograms;
        this.halfKilograms = halfKilograms;
        this.quarterKilograms = quarterKilograms;
        this.tomnKilograms = tomnKilograms;
    }

    public Double getKilograms() {
        return kilograms;
    }

    public void setKilograms(Double kilograms) {
        this.kilograms = kilograms;
    }

    public Double getHalfKilograms() {
        return halfKilograms;
    }

    public void setHalfKilograms(Double halfKilograms) {
        this.halfKilograms = halfKilograms;
    }

    public Double getQuarterKilograms() {
        return quarterKilograms;
    }

    public void setQuarterKilograms(Double quarterKilograms) {
        this.quarterKilograms = quarterKilograms;
    }

    public Double getTomnKilograms() {
        return tomnKilograms;
    }

    public void setTomnKilograms(Double tomnKilograms) {
        this.tomnKilograms = tomnKilograms;
    }

}
