package com.castleglobal_clean.presentation.presenters;

import com.castleglobal_clean.domain.model.SearchResult;
import com.castleglobal_clean.presentation.presenters.base.BasePresenter;

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

    void setView(View view);
    void load(String queryString) throws Exception;

}
