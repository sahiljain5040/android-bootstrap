package com.demo.data.search.repository

import com.demo.data.search.network.api.SearchApi
import com.demo.domain.base.Constants
import com.demo.domain.search.model.RestaurantWrapper
import com.demo.domain.search.repository.RestaurantRepository
import io.reactivex.Observable
import javax.inject.Inject

class RestaurantApiRepository @Inject constructor(protected val searchApi: SearchApi):RestaurantRepository{

    override fun getRestaurants(queryParams: Map<String, String>): Observable<List<RestaurantWrapper>> {
        return searchApi.getRestaurantsObservable(Constants.API_KEY, queryParams)
                .map { apiResponse -> apiResponse.restaurants }
    }

}