package com.demo.domain.model;

/**
 * Created by sahil on 10/16/17.
 */

public class Cuisine {

    private int cuisineId;
    private String cuisineName;

    public int getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(int cuisineId) {
        this.cuisineId = cuisineId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "cuisineId=" + cuisineId +
                ", cuisineName='" + cuisineName + '\'' +
                '}';
    }
}
