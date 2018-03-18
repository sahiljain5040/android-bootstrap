package com.castleglobal_clean.domain.interactors;

import com.castleglobal_clean.domain.executor.Executor;
import com.castleglobal_clean.domain.executor.MainThread;
import com.castleglobal_clean.domain.model.SearchResult;
import com.castleglobal_clean.domain.repository.RestaurantRepository;
import com.castleglobal_clean.utils.SearchResultsHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by sahil on 3/17/18.
 */

public class GetSearchRestaurantUseCase extends UseCase<List<SearchResult>, Map<String, String>>{

    private RestaurantRepository mRepository;

    public GetSearchRestaurantUseCase(Executor threadExecutor, MainThread mainThread,
                                      RestaurantRepository respository) {
        super(threadExecutor, mainThread);
        this.mRepository = respository;
    }


    @Override
    public Observable<List<SearchResult>> buildUseCaseObservable(Map<String, String> queryParams) {
        return mRepository.getRestaurants(queryParams)
                .map(SearchResultsHelper::getRestaurantsByCuisineType)
                .map(SearchResultsHelper::getSearchResultList);
    }
}
