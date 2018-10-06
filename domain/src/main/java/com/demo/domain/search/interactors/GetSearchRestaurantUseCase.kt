package com.demo.domain.search.interactors

import com.demo.domain.base.executor.Executor
import com.demo.domain.base.executor.PostExecutionThread
import com.demo.domain.base.interactors.UseCase
import com.demo.domain.search.model.SearchResult
import com.demo.domain.search.repository.RestaurantRepository
import com.demo.domain.search.utils.SearchResultsHelper
import io.reactivex.Observable
import javax.inject.Inject

class GetSearchRestaurantUseCase @Inject constructor(override val threadExecutor: Executor,
                                                     override val postExecutionThread: PostExecutionThread,
                                                     val repository: RestaurantRepository,
                                                     val searchResultsHelper: SearchResultsHelper)
    : UseCase<List<SearchResult>, Map<String, String>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Map<String, String>): Observable<List<SearchResult>> {
        return repository.getRestaurants(queryParams = params)
                .map { restaurantList -> searchResultsHelper.getRestaurantsByCuisineType(restaurantList) }
                .map { restaurantsByCuisineMap -> searchResultsHelper.getSearchResultList(restaurantsByCuisineMap) }
    }

}