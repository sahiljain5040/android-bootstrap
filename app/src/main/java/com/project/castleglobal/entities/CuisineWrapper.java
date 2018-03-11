package com.project.castleglobal.entities;

/**
 * Created by sahil on 10/16/17.
 */

public class CuisineWrapper {

    private Cuisine cuisine;

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "CuisineWrapper{" +
                "cuisine=" + cuisine +
                '}';
    }
}
