package com.castleglobal_clean.presentation.presenters.impl;

import com.castleglobal_clean.domain.interactors.RestaurantInteractor;
import com.castleglobal_clean.domain.model.RestaurantWrapper;
import com.castleglobal_clean.presentation.presenters.SearchPresenter;
import com.castleglobal_clean.presentation.presenters.base.AbstractPresenter;
import com.castleglobal_clean.utils.SearchResultsHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 3/17/18.
 */

public class SearchPresenterImpl extends AbstractPresenter implements SearchPresenter,
        RestaurantInteractor.Callback{

    private RestaurantInteractor mRestaurantInteractor;
    private SearchPresenter.View mView;

    public SearchPresenterImpl(RestaurantInteractor restaurantInteractor) {
        super();
        this.mRestaurantInteractor = restaurantInteractor;
    }

    @Override
    public void setView(View view) {
        this.mView = view;
    }

    @Override
    public void load(String queryString) {

        if(mView != null){
            mView.onSearchResultsLoading();
        }else{
            return;
        }
        mRestaurantInteractor.setQueryParams(getQueryParams(queryString));
        mRestaurantInteractor.execute();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        mView = null;
    }

    @Override
    public void onRestaurantsRetrieved(List<RestaurantWrapper> restaurantList) {
        if(mView != null){
            mView.onSearchResultsLoaded(SearchResultsHelper.getSearchResultList
                    (SearchResultsHelper.getRestaurantsByCuisineType(restaurantList)));
        }
    }

    @Override
    public void onRetrievalFailed() {
        if (mView != null) {
            mView.onSearchResultsFailed();
        }
    }

    private Map<String, String> getQueryParams(String searchQuery) {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", searchQuery);
        return queryParams;
    }
}
