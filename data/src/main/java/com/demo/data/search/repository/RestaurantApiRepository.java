package com.demo.data.search.repository;

import com.demo.data.search.network.api.SearchApi;
import com.demo.data.search.network.response.ApiSearchRestaurantsResponse;
import com.demo.domain.base.Constants;
import com.demo.domain.search.model.RestaurantWrapper;
import com.demo.domain.search.repository.RestaurantRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by sahil on 3/17/18.
 */
public class RestaurantApiRepository implements RestaurantRepository {

    private SearchApi mSearchApi;

    @Inject
    public RestaurantApiRepository(SearchApi searchApi){
        mSearchApi = searchApi;
    }

    @Override
    public Observable<List<RestaurantWrapper>> getRestaurants(Map<String, String> queryParams) {
        return mSearchApi.getRestaurantsObservable(Constants.API_KEY, queryParams)
                .map(ApiSearchRestaurantsResponse::getRestaurants);
    }
}
