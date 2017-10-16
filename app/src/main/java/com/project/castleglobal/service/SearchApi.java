package com.project.castleglobal.service;

import com.project.castleglobal.model.ApiCuisinesResponse;
import com.project.castleglobal.model.ApiSearchRestaurantsResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by sahil on 10/14/17.
 */

public interface SearchApi {

    @GET("api/v2.1/cuisines")
    Observable<ApiCuisinesResponse> getCuisines(@Header("user-key") String apiKey, @Query("city_id") int cityId);

    @GET("api/v2.1/search")
    Observable<ApiSearchRestaurantsResponse> getRestaurants(@Header("user-key") String apiKey,
                                                            @QueryMap Map<String, String> queryParams);
}
