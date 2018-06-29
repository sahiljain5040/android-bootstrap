package com.demo.domain.search.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by sahil on 10/14/17.
 */

public class UserRating implements Serializable{

    private String aggregateRating;
    private String ratingText;
    private String ratingColor;

    public String getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
