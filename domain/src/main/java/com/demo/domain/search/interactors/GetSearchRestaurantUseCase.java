package com.demo.domain.search.interactors;


import com.demo.domain.base.executor.Executor;
import com.demo.domain.base.executor.PostExecutionThread;
import com.demo.domain.base.interactors.UseCase;
import com.demo.domain.search.model.SearchResult;
import com.demo.domain.search.repository.RestaurantRepository;
import com.demo.domain.search.utils.SearchResultsHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by sahil on 3/17/18.
 */
public class GetSearchRestaurantUseCase extends UseCase<List<SearchResult>, Map<String, String>> {

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
                .map(SearchResultsHelper::getRestaurantsByCuisineType)
                .map(SearchResultsHelper::getSearchResultList);
    }
}
