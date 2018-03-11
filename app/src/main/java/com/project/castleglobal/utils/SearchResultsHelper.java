package com.project.castleglobal.utils;

import com.project.castleglobal.entities.Restaurant;
import com.project.castleglobal.entities.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sahil on 3/10/18.
 */

public class SearchResultsHelper {

    public static ArrayList<SearchResult> getSearchResultList(HashMap<String, ArrayList<Restaurant>> restaurantsByCuisines) {
        ArrayList<SearchResult> searchResultsList = new ArrayList<>();
        ArrayList<String> cuisinesList = new ArrayList<>(restaurantsByCuisines.keySet());
        for (String cuisine : cuisinesList) {
            SearchResult searchResult = new SearchResult();
            searchResult.setRestaurant(false);
            searchResult.setName(cuisine);
            searchResultsList.add(searchResult);
            ArrayList<Restaurant> restaurants = restaurantsByCuisines.get(cuisine);
            for (Restaurant restaurant : restaurants) {
                searchResult = new SearchResult();
                searchResult.setRestaurant(true);
                searchResult.setRestaurant(restaurant);
                searchResultsList.add(searchResult);
            }
        }
        return searchResultsList;
    }
}
