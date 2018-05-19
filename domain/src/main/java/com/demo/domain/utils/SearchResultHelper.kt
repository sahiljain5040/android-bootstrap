package com.demo.domain.utils

import com.demo.domain.model.Restaurant
import com.demo.domain.model.RestaurantWrapper
import com.demo.domain.model.SearchResult
import java.util.*
import kotlin.collections.ArrayList

fun getRestaurantsByCuisineType(restaurantsList: List<RestaurantWrapper>): HashMap<String, ArrayList<Restaurant>> {
    val restaurantsByCuisine = HashMap<String, ArrayList<Restaurant>>()

    for (restaurantWrapper in restaurantsList) {
        val restaurant = restaurantWrapper.restaurant
        val cuisinesList = restaurantWrapper.restaurant.cuisines.split(", ")
        for (cuisineOffered in cuisinesList) {
            val cuisineOfferingRestaurants = ArrayList<Restaurant>(restaurantsByCuisine[cuisineOffered])
            cuisineOfferingRestaurants.add(restaurant)
            restaurantsByCuisine[cuisineOffered] = cuisineOfferingRestaurants
        }
    }
    return restaurantsByCuisine
}

fun getSearchResultList(restaurantsByCuisines: HashMap<String, ArrayList<Restaurant>>): ArrayList<SearchResult> {
    val searchResultsList = ArrayList<SearchResult>()
    val cuisinesList = ArrayList<String>(restaurantsByCuisines.keys)
    for (cuisine in cuisinesList){
        searchResultsList.add(SearchResult(isRestaurant = false, name = cuisine, restaurant = null))
        val restaurantList = restaurantsByCuisines[cuisine]
        for (restaurant in restaurantList!!){
            searchResultsList.add(SearchResult(isRestaurant = true, name = restaurant.name, restaurant = restaurant))
        }
    }
    return searchResultsList
}