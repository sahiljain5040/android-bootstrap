package com.project.castleglobal.view.delegate;

import com.project.castleglobal.model.SearchResult;

import java.util.ArrayList;

/**
 * Created by sahil on 10/16/17.
 */

public interface SearchResultsDelegate {

    void onSearchResultsLoading();
    void onSearchResultsLoaded(ArrayList<SearchResult> searchResults);
    void onSearchResultsFailed();
}
