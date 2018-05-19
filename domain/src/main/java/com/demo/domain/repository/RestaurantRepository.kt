package com.demo.domain.repository

import com.demo.domain.model.RestaurantWrapper
import io.reactivex.Observable

interface RestaurantRepository {

    fun getRestaurants(queryParams: Map<String, String>): Observable<List<RestaurantWrapper>>;

}