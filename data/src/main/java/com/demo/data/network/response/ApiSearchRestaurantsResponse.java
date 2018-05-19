package com.demo.data.network.response;


import java.util.ArrayList;

/**
 * Created by sahil on 10/14/17.
 */

public class ApiSearchRestaurantsResponse {

    private int resultsFound;
    private ArrayList<RestaurantWrapper> restaurants = new ArrayList<RestaurantWrapper>();

    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }

    public ArrayList<RestaurantWrapper> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<RestaurantWrapper> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "ApiSearchRestaurantsResponse{" +
                "resultsFound=" + resultsFound +
                ", restaurants=" + restaurants +
                '}';
    }
}
