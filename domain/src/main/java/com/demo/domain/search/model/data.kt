package com.demo.domain.search.model

import com.demo.domain.search.utils.Optional
import java.io.Serializable

data class Cuisine(var cuisineId: Int, var cuisineName: String): Serializable

data class CuisineWrapper(var cuisine: Cuisine): Serializable

data class Restaurant(var id: String, var name: String, var url: String, var cuisines: String,
                      var averageCostForTwo: Int, var thumb: String, var userRating: UserRating): Serializable

data class RestaurantWrapper(var restaurant: Restaurant): Serializable

data class SearchResult(var isRestaurant: Boolean, var name: Optional<String>, var restaurant: Optional<Restaurant>): Serializable

data class UserRating(var aggregateRating: String, var ratingText: String, var ratingColor: String): Serializable