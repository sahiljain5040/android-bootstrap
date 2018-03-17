package com.castleglobal_clean.storage;

import com.castleglobal_clean.domain.model.RestaurantWrapper;
import com.castleglobal_clean.domain.repository.RestaurantRepository;
import com.castleglobal_clean.storage.network.api.SearchApi;
import com.castleglobal_clean.storage.network.response.ApiSearchRestaurantsResponse;
import com.castleglobal_clean.utils.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by sahil on 3/17/18.
 */

public class RestaurantApiRepository implements RestaurantRepository{

    private SearchApi mSearchApi;

    public RestaurantApiRepository(Retrofit retrofit){
        mSearchApi = retrofit.create(SearchApi.class);
    }

    @Override
    public List<RestaurantWrapper> getRestaurants(Map<String, String> queryParams) {
        Call<ApiSearchRestaurantsResponse> call = mSearchApi.getRestaurants(Constants.API_KEY, queryParams);
        try {
            Response<ApiSearchRestaurantsResponse> response = call.execute();
            ApiSearchRestaurantsResponse apiResponse = response.body();
            return apiResponse.getRestaurants();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
