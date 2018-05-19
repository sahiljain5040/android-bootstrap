package com.demo.data.network.response

import com.demo.domain.model.CuisineWrapper
import com.demo.domain.model.RestaurantWrapper
import java.util.*

data class ApiCuisinesResponse(val cuisines: ArrayList<CuisineWrapper>)

data class ApiSearchRestaurantsResponse(val resultsFound: Int, val restaurants: ArrayList<RestaurantWrapper>)