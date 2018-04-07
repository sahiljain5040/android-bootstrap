package com.demo.domain.model;

import com.google.gson.Gson;

/**
 * Created by sahil on 10/17/17.
 */

public class RestaurantWrapper {

    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
