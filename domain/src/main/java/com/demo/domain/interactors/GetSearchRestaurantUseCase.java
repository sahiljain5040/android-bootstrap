package com.demo.domain.interactors;


import com.demo.domain.executor.Executor;
import com.demo.domain.executor.PostExecutionThread;
import com.demo.domain.model.Restaurant;
import com.demo.domain.model.RestaurantWrapper;
import com.demo.domain.model.SearchResult;
import com.demo.domain.repository.RestaurantRepository;
import com.demo.domain.utils.SearchResultsHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by sahil on 3/17/18.
 */
public class GetSearchRestaurantUseCase extends UseCase<List<SearchResult>, Map<String, String>>{

    private RestaurantRepository mRepository;

    @Inject
    public GetSearchRestaurantUseCase(Executor threadExecutor, PostExecutionThread postExecutionThread,
                                      RestaurantRepository respository) {
        super(threadExecutor, postExecutionThread);
        this.mRepository = respository;
    }


    @Override
    public Observable<List<SearchResult>> buildUseCaseObservable(Map<String, String> queryParams) {
        return mRepository.getRestaurants(queryParams)
                .map(new Function<List<RestaurantWrapper>, HashMap<String, ArrayList<Restaurant>>>() {
                    @Override
                    public HashMap<String, ArrayList<Restaurant>> apply(List<RestaurantWrapper> restaurantWrappers) throws Exception{
                        return SearchResultsHelper.getRestaurantsByCuisineType(restaurantWrappers);
                    }
                })
                .map(new Function<HashMap<String, ArrayList<Restaurant>>, List<SearchResult>>() {
                    @Override
                    public List<SearchResult> apply(HashMap<String, ArrayList<Restaurant>> stringArrayListHashMap) throws Exception {
                        return SearchResultsHelper.getSearchResultList(stringArrayListHashMap);
                    }
                });
    }
}
