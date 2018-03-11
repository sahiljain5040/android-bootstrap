package com.project.castleglobal.contracts;

import com.project.castleglobal.entities.Restaurant;
import com.project.castleglobal.entities.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by sahil on 3/10/18.
 */

public interface SearchMVP {

    interface View{
        void onSearchResultsLoading();
        void onSearchResultsLoaded(ArrayList<SearchResult> searchResults);
        void onSearchResultsFailed();
    }

    interface Presenter{
        void setView(View view);
        void load(String searchQuery);
        void onDestroy();
    }

    interface Model{
        Observable<HashMap<String, ArrayList<Restaurant>>> getRestaurants(Map<String, String> queryParams);
    }
}
