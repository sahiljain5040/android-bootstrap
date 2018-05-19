package com.demo.domain.model

import java.io.Serializable

data class Cuisine(val cuisineId: Int, val cuisineName: String) : Serializable

data class CuisineWrapper(val cuisine: Cuisine) : Serializable

data class Restaurant(val id: String, val name: String, val url: String, val cuisines: String,
                      val averageCostForTwo: Int, val thumb: String, val userRating: UserRating) : Serializable

public data class RestaurantWrapper(val restaurant: Restaurant) : Serializable

data class SearchResult(val isRestaurant: Boolean = true, val name: String, val restaurant: Restaurant?)
    : Serializable

data class UserRating(val aggregateRating: String, val ratingText: String, val ratingColor: String)
    : Serializable