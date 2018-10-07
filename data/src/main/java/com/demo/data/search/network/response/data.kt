package com.demo.data.search.network.response

import com.demo.domain.search.model.CuisineWrapper
import com.demo.domain.search.model.RestaurantWrapper
import java.io.Serializable
import java.util.*

data class ApiSearchRestaurantsResponse(var resultsFound: Int, var restaurants: ArrayList<RestaurantWrapper>): Serializable

data class ApiCuisinesResponse(var cuisines: ArrayList<CuisineWrapper>): Serializable