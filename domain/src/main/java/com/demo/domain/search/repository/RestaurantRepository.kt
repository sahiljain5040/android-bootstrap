package com.demo.domain.search.repository

import com.demo.domain.search.model.RestaurantWrapper
import io.reactivex.Observable

interface RestaurantRepository {

    fun getRestaurants(queryParams: Map<String, String>): Observable<List<RestaurantWrapper>>
}