package com.project.castleglobal.service;


import com.project.castleglobal.model.ApiSearchRestaurantsResponse;
import com.project.castleglobal.utils.Constants;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by sahil on 10/14/17.
 */
@Singleton
public class SearchService {

    private SearchApi mZomatoApi;

    @Inject
    public SearchService(Retrofit retrofit) {
        mZomatoApi = retrofit.create(SearchApi.class);
    }

    public Observable<ApiSearchRestaurantsResponse> getRestaurants(Map<String, String> queryParams) {
        return mZomatoApi
                .getRestaurants(Constants.API_KEY, queryParams)
                .subscribeOn(Schedulers.io());
    }
}
