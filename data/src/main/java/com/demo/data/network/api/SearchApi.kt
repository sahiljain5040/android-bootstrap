package com.demo.data.network.api

import com.demo.data.network.response.ApiCuisinesResponse
import com.demo.data.network.response.ApiSearchRestaurantsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchApi {

    fun getCuisines(@Header("user-key") apiKey: String, @Query("city_id") cityId: Int):
            Observable<ApiCuisinesResponse>

    fun getRestaurantsObservable(@Header("user-key") apiKey: String, @QueryMap queryParams: Map<String, String> )
            : Observable<ApiSearchRestaurantsResponse>

    fun getRestaurants(@Header("user-key") apiKey: String, @QueryMap queryParams: Map<String, String>):
            Call<ApiSearchRestaurantsResponse>
}