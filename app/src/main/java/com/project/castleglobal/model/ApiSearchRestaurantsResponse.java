package com.project.castleglobal.model;

import java.util.ArrayList;

/**
 * Created by sahil on 10/14/17.
 */

public class ApiSearchRestaurantsResponse {

    private int resultsFound;
    private ArrayList<Restaurant> restaurants;

    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
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
