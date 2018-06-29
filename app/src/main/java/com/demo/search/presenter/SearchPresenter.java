package com.demo.search.presenter;

import com.demo.base.presenter.BasePresenter;
import com.demo.domain.search.model.SearchResult;

import java.util.List;

/**
 * Created by sahil on 3/17/18.
 */

public interface SearchPresenter extends BasePresenter{

    interface View{
        void onSearchResultsLoading();
        void onSearchResultsLoaded(List<SearchResult> searchResults);
        void onSearchResultsFailed();

    }

    void load(String queryString) throws Exception;
}
