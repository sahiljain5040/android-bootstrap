package com.demo.search.presenter.impl;

import com.demo.domain.search.interactors.GetSearchRestaurantUseCase;
import com.demo.domain.base.interactors.UseCaseObserver;
import com.demo.domain.search.model.SearchResult;
import com.demo.search.presenter.SearchPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by sahil on 3/17/18.
 */
public class SearchPresenterImpl implements SearchPresenter{

    private GetSearchRestaurantUseCase mSearchRestaurantUseCase;
    private SearchPresenter.View mView;

    @Inject
    public SearchPresenterImpl(View view, GetSearchRestaurantUseCase restaurantInteractor) {
        super();
        this.mView = view;
        this.mSearchRestaurantUseCase = restaurantInteractor;
    }

    @Override
    public void load(String queryString) throws Exception {

        if(mView != null){
            mView.onSearchResultsLoading();
        }else{
            throw new Exception("setView() not called Before calling load()");
        }

        mSearchRestaurantUseCase.dispose();
        mSearchRestaurantUseCase.execute(getSearchListObserver(), getQueryParams(queryString));
    }

    private UseCaseObserver<List<SearchResult>> getSearchListObserver(){

        return new UseCaseObserver<List<SearchResult>>(){
            @Override
            public void onNext(List<SearchResult> searchResults) {
                super.onNext(searchResults);
                mView.onSearchResultsLoaded(searchResults);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                mView.onSearchResultsFailed();
            }
        };
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
        mSearchRestaurantUseCase.dispose();
    }

    private Map<String, String> getQueryParams(String searchQuery) {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", searchQuery);
        return queryParams;
    }
}
