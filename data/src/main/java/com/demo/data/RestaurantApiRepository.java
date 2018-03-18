package com.demo.data;

import com.demo.data.network.api.SearchApi;
import com.demo.data.network.response.ApiSearchRestaurantsResponse;
import com.demo.domain.model.RestaurantWrapper;
import com.demo.domain.repository.RestaurantRepository;
import com.demo.domain.utils.Constants;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * Created by sahil on 3/17/18.
 */
public class RestaurantApiRepository implements RestaurantRepository {

    private SearchApi mSearchApi;

    @Inject
    public RestaurantApiRepository(Retrofit retrofit){
        mSearchApi = retrofit.create(SearchApi.class);
    }

    @Override
    public Observable<List<RestaurantWrapper>> getRestaurants(Map<String, String> queryParams) {
        return mSearchApi.getRestaurantsObservable(Constants.API_KEY, queryParams)
                .map(new Function<ApiSearchRestaurantsResponse, List<RestaurantWrapper>>() {
                    @Override
                    public List<RestaurantWrapper> apply(ApiSearchRestaurantsResponse apiSearchRestaurantsResponse) throws Exception {
                        return apiSearchRestaurantsResponse.getRestaurants();
                    }
                });
    }
}
