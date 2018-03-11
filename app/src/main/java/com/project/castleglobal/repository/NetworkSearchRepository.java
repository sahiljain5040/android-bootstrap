package com.project.castleglobal.repository;

import com.project.castleglobal.contracts.SearchRepository;
import com.project.castleglobal.entities.Restaurant;
import com.project.castleglobal.entities.RestaurantWrapper;
import com.project.castleglobal.network.api.SearchApi;
import com.project.castleglobal.network.response.ApiSearchRestaurantsResponse;
import com.project.castleglobal.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by sahil on 3/10/18.
 */

public class NetworkSearchRepository implements SearchRepository{

    private SearchApi mSearchApi;

    @Inject
    public NetworkSearchRepository(Retrofit retrofit){
        mSearchApi = retrofit.create(SearchApi.class);
    }

    @Override
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
}
