package com.demo.domain.interactors

import com.demo.domain.executor.Executor
import com.demo.domain.executor.PostExecutionThread
import com.demo.domain.model.SearchResult
import com.demo.domain.repository.RestaurantRepository
import com.demo.domain.utils.getRestaurantsByCuisineType
import com.demo.domain.utils.getSearchResultList
import io.reactivex.Observable

class GetSearchRestaurantUseCase(val executor: Executor, val postExecutionThread: PostExecutionThread,
                                 val repository: RestaurantRepository)
    : UseCase<List<SearchResult>, Map<String, String>>(executor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Map<String, String>): Observable<List<SearchResult>> {
        return repository.getRestaurants(params)
                .map { restaurantWrappers -> getRestaurantsByCuisineType(restaurantWrappers) }
                .map { restaurantsListByCuisineMap -> getSearchResultList(restaurantsListByCuisineMap) }
    }

}