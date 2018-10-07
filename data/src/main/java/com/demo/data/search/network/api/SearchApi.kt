package com.demo.data.search.network.api

import com.demo.data.search.network.response.ApiCuisinesResponse
import com.demo.data.search.network.response.ApiSearchRestaurantsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchApi {

    @GET("api/v2.1/cuisines")
    fun getCuisines(@Header("user-key") apiKey: String, @Query("city_id") cityId: Int)
            : Observable<ApiCuisinesResponse>

    @GET("api/v2.1/search")
    fun getRestaurantsObservable(@Header("user-key") apiKey: String, @QueryMap queryParams: Map<String, String>)
            : Observable<ApiSearchRestaurantsResponse>

    @GET("api/v2.1/search")
    fun getRestaurants(@Header("user-key") apiKey: String, @QueryMap queryParams: Map<String, String>)
            : Call<ApiSearchRestaurantsResponse>
}