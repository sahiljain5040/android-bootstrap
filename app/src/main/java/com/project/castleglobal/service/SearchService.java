package com.project.castleglobal.service;


import com.project.castleglobal.model.ApiSearchRestaurantsResponse;
import com.project.castleglobal.model.Restaurant;
import com.project.castleglobal.model.RestaurantWrapper;
import com.project.castleglobal.model.SearchResult;
import com.project.castleglobal.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by sahil on 10/14/17.
 */
@Singleton
public class SearchService {

    private SearchApi mSearchApi;

    @Inject
    public SearchService(Retrofit retrofit) {
        mSearchApi = retrofit.create(SearchApi.class);
    }

    public Observable<HashMap<String, ArrayList<Restaurant>>> getRestaurants(Map<String, String> queryParams) {
        return mSearchApi.getRestaurants(Constants.API_KEY, queryParams)
                .map(this::getRestaurantsByCuisineType)
                .subscribeOn(Schedulers.io());
    }

    private HashMap<String, ArrayList<Restaurant>> getRestaurantsByCuisineType(ApiSearchRestaurantsResponse restaurantsResponse) {
        HashMap<String, ArrayList<Restaurant>> restaurantsByCuisine = new HashMap<String, ArrayList<Restaurant>>();
        ArrayList<RestaurantWrapper> restaurantsList = restaurantsResponse.getRestaurants();

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

    public ArrayList<SearchResult> getSearchResultList(HashMap<String, ArrayList<Restaurant>> restaurantsByCuisines) {
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
