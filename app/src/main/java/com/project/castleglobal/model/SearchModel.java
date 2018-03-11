package com.project.castleglobal.model;


import com.project.castleglobal.contracts.SearchMVP;
import com.project.castleglobal.contracts.SearchRepository;
import com.project.castleglobal.entities.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by sahil on 10/14/17.
 */
@Singleton
public class SearchModel implements SearchMVP.Model{


    private SearchRepository mSearchResultRepository;

    @Inject
    public SearchModel(SearchRepository searchRepository) {
        mSearchResultRepository = searchRepository;
    }

    @Override
    public Observable<HashMap<String, ArrayList<Restaurant>>> getRestaurants(Map<String, String> queryParams) {
        return mSearchResultRepository.getRestaurants(queryParams);
    }
}
