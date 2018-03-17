package com.castleglobal_clean.utils;

import com.castleglobal_clean.domain.model.Restaurant;
import com.castleglobal_clean.domain.model.RestaurantWrapper;
import com.castleglobal_clean.domain.model.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sahil on 3/10/18.
 */

public class SearchResultsHelper {

    public static HashMap<String, ArrayList<Restaurant>> getRestaurantsByCuisineType(List<RestaurantWrapper> restaurantsList) {
        HashMap<String, ArrayList<Restaurant>> restaurantsByCuisine = new HashMap<String, ArrayList<Restaurant>>();

        for (RestaurantWrapper restaurantWrapper : restaurantsList) {
            Restaurant restaurant = restaurantWrapper.getRestaurant();
            String cuisinesOffered = restaurant.getCuisines();
            String[] cuisinesList = cuisinesOffered.split(", ");
            for (String cuisineOffered : cuisinesList) {
                ArrayList<Restaurant> cuisineOfferingRestaurant;
                if (restaurantsByCuisine.containsKey(cuisineOffered)) {
                    cuisineOfferingRestaurant = restaurantsByCuisine.get(cuisineOffered);
                    cuisineOfferingRestaurant.add(restaurant);
                } else {
                    cuisineOfferingRestaurant = new ArrayList<Restaurant>();
                    cuisineOfferingRestaurant.add(restaurant);
                }
                restaurantsByCuisine.put(cuisineOffered, cuisineOfferingRestaurant);
            }
        }
        return restaurantsByCuisine;
    }

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
