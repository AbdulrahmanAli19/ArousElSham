package com.example.arouselsham.pojo.model.maleModels;

import java.io.Serializable;

public class PriceByPiece implements Serializable {

    private Double holeChicken ;
    private Double halfChicken ;
    private Double quarterChicken ;

    public PriceByPiece() {
    }

    public PriceByPiece(Double holeChicken, Double halfChicken, Double quarterChicken) {
        this.holeChicken = holeChicken;
        this.halfChicken = halfChicken;
        this.quarterChicken = quarterChicken;
    }

    public Double getHoleChicken() {
        return holeChicken;
    }

    public void setHoleChicken(Double holeChicken) {
        this.holeChicken = holeChicken;
    }

    public Double getHalfChicken() {
        return halfChicken;
    }

    public void setHalfChicken(Double halfChicken) {
        this.halfChicken = halfChicken;
    }

    public Double getQuarterChicken() {
        return quarterChicken;
    }

    public void setQuarterChicken(Double quarterChicken) {
        this.quarterChicken = quarterChicken;
    }
}
