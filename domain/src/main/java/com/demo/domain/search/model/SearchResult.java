package com.demo.domain.search.model;

/**
 * Created by sahil on 10/17/17.
 */

public class SearchResult {

    private boolean isRestaurant = true;
    private String name;
    private Restaurant restaurant;

    public boolean isRestaurant() {
        return isRestaurant;
    }

    public void setRestaurant(boolean restaurant) {
        isRestaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "isRestaurant=" + isRestaurant +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
