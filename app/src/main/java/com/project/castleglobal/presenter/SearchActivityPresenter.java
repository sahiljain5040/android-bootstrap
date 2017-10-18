package com.project.castleglobal.presenter;

import com.project.castleglobal.service.SearchService;
import com.project.castleglobal.view.delegate.SearchResultsDelegate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sahil on 10/16/17.
 */

public class SearchActivityPresenter {

    private SearchResultsDelegate mView;
    private SearchService mSearchService;
    private Subscription mSubscription;

    @Inject
    public SearchActivityPresenter(SearchService searchService) {
        mSearchService = searchService;
    }

    public void setView(SearchResultsDelegate searchResultsDelegate) {
        mView = searchResultsDelegate;
    }

    public void load(String searchQuery) {

        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }

        if(mView != null){
            mView.onSearchResultsLoading();
        }else{
            return;
        }

        mSubscription = mSearchService.getRestaurants(getQueryParams(searchQuery))
                .observeOn(AndroidSchedulers.mainThread())
                .map(restaurantsByCuisine -> mSearchService.getSearchResultList(restaurantsByCuisine))
                .subscribe(searchResultsList -> {
                    if (mView != null) {
                        mView.onSearchResultsLoaded(searchResultsList);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    if (mView != null) {
                        mView.onSearchResultsFailed();
                    }
                });
    }

    private Map<String, String> getQueryParams(String searchQuery) {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", searchQuery);
        return queryParams;
    }

    public void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

}
