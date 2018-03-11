package com.project.castleglobal.presenter;

import com.project.castleglobal.contracts.SearchMVP;
import com.project.castleglobal.model.SearchModel;
import com.project.castleglobal.utils.SearchResultsHelper;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sahil on 10/16/17.
 */

public class SearchActivityPresenter implements SearchMVP.Presenter{

    private SearchMVP.View mView;
    private SearchMVP.Model mSearchModel;
    private Subscription mSubscription;

    @Inject
    public SearchActivityPresenter(SearchModel searchModel) {
        mSearchModel = searchModel;
    }

    @Override
    public void setView(SearchMVP.View view) {
        this.mView = view;
    }

    @Override
    public void load(String searchQuery) {

        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }

        if(mView != null){
            mView.onSearchResultsLoading();
        }else{
            return;
        }

        mSubscription = mSearchModel.getRestaurants(getQueryParams(searchQuery))
                .observeOn(AndroidSchedulers.mainThread())
                .map(SearchResultsHelper::getSearchResultList)
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

    @Override
    public void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        mView = null;
    }

}
