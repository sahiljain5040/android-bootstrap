package com.project.castleglobal.presenter;

import android.util.Log;

import com.project.castleglobal.service.SearchService;
import com.project.castleglobal.view.delegate.SearchResultsDelegate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sahil on 10/16/17.
 */

public class SearchActivityPresenter {

    private SearchResultsDelegate mView;
    private SearchService mSearchService;

    @Inject
    public SearchActivityPresenter(SearchService searchService) {
        mSearchService = searchService;
    }

    public void setView(SearchResultsDelegate searchResultsDelegate) {
        mView = searchResultsDelegate;
    }

    public void load() {
        mSearchService.getRestaurants(getQueryParams())
                .observeOn(AndroidSchedulers.mainThread())
                .map(restaurantsByCuisine -> mSearchService.getSearchResultList(restaurantsByCuisine))
                .subscribe(searchResultsList -> {
                    Log.d("Sahil", "searchResultsList: " + searchResultsList);
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }

    private Map<String, String> getQueryParams() {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", "bagel");
        return queryParams;
    }

    public void onDestroy() {
        mView = null;
    }

}
