package com.demo.data.repository

import com.demo.data.network.api.SearchApi
import com.demo.domain.model.RestaurantWrapper
import com.demo.domain.repository.RestaurantRepository
import com.demo.domain.utils.API_KEY
import io.reactivex.Observable

class RestaurantApiRepository(private val searchApi: SearchApi): RestaurantRepository {

    override fun getRestaurants(queryParams: Map<String, String>): Observable<List<RestaurantWrapper>> {
        return searchApi.getRestaurantsObservable(API_KEY, queryParams)
                .map { apiSearchRestaurantsResponse -> apiSearchRestaurantsResponse.restaurants }
    }

}