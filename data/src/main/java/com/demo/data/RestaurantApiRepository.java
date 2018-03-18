package com.demo.data;

import com.demo.data.network.api.SearchApi;
import com.demo.data.network.response.ApiSearchRestaurantsResponse;
import com.demo.domain.model.RestaurantWrapper;
import com.demo.domain.repository.RestaurantRepository;
import com.demo.domain.utils.Constants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by sahil on 3/17/18.
 */

public class RestaurantApiRepository implements RestaurantRepository {

    private SearchApi mSearchApi;

    public RestaurantApiRepository(Retrofit retrofit){
        mSearchApi = retrofit.create(SearchApi.class);
    }

    @Override
    public Observable<List<RestaurantWrapper>> getRestaurants(Map<String, String> queryParams) {
        return mSearchApi.getRestaurantsObservable(Constants.API_KEY, queryParams)
                .map(ApiSearchRestaurantsResponse::getRestaurants);
    }
}
