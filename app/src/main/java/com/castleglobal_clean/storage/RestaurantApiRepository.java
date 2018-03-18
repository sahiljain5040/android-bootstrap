package com.castleglobal_clean.storage;

import com.castleglobal_clean.domain.model.RestaurantWrapper;
import com.castleglobal_clean.domain.repository.RestaurantRepository;
import com.castleglobal_clean.storage.network.api.SearchApi;
import com.castleglobal_clean.storage.network.response.ApiSearchRestaurantsResponse;
import com.castleglobal_clean.utils.Constants;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
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
    public Observable<List<RestaurantWrapper>> getRestaurants(Map<String, String> queryParams) {
        return mSearchApi.getRestaurantsObservable(Constants.API_KEY, queryParams)
                .map(ApiSearchRestaurantsResponse::getRestaurants);
    }
}
