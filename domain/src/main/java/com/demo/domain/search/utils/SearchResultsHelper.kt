package com.demo.domain.search.utils

import com.demo.domain.search.model.Restaurant
import com.demo.domain.search.model.RestaurantWrapper
import com.demo.domain.search.model.SearchResult
import java.util.*
import javax.inject.Inject

class SearchResultsHelper @Inject constructor(){

    fun getRestaurantsByCuisineType(restaurantsList: List<RestaurantWrapper>): Map<String, List<Restaurant>> {
        val restaurantsByCuisine = HashMap<String, ArrayList<Restaurant>>()

        for ((restaurant) in restaurantsList) {
            val cuisinesOffered = restaurant.cuisines
            val cuisinesList = cuisinesOffered.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (cuisineOffered in cuisinesList) {
                val cuisineOfferingRestaurants: ArrayList<Restaurant>?
                if (restaurantsByCuisine.containsKey(cuisineOffered)) {
                    cuisineOfferingRestaurants = restaurantsByCuisine[cuisineOffered]
                    cuisineOfferingRestaurants!!.add(restaurant)
                } else {
                    cuisineOfferingRestaurants = ArrayList()
                    cuisineOfferingRestaurants.add(restaurant)
                }
                restaurantsByCuisine[cuisineOffered] = cuisineOfferingRestaurants
            }
        }
        return restaurantsByCuisine
    }

    fun getSearchResultList(restaurantsByCuisines: Map<String, List<Restaurant>>): List<SearchResult>{
        val searchResultsList = ArrayList<SearchResult>()
        val cuisinesList = ArrayList(restaurantsByCuisines.keys)
        for (cuisine in cuisinesList) {
            var searchResult = SearchResult(false, Optional.of(cuisine), Optional.empty())
            searchResultsList.add(searchResult)
            val restaurants = restaurantsByCuisines[cuisine]
            for (restaurant in restaurants!!) {
                searchResult = SearchResult(true, Optional.empty(), Optional.of(restaurant))
                searchResultsList.add(searchResult)
            }
        }
        return searchResultsList
    }

}