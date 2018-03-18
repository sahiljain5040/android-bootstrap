package com.demo.data.network.api;

import com.demo.data.network.response.ApiCuisinesResponse;
import com.demo.data.network.response.ApiSearchRestaurantsResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by sahil on 10/14/17.
 */

public interface SearchApi {

    @GET("api/v2.1/cuisines")
    Observable<ApiCuisinesResponse> getCuisines(@Header("user-key") String apiKey, @Query("city_id") int cityId);

    @GET("api/v2.1/search")
    Observable<ApiSearchRestaurantsResponse> getRestaurantsObservable(@Header("user-key") String apiKey,
                                                            @QueryMap Map<String, String> queryParams);

    @GET("api/v2.1/search")
    Call<ApiSearchRestaurantsResponse> getRestaurants(@Header("user-key") String apiKey,
                                                      @QueryMap Map<String, String> queryParams);
}
