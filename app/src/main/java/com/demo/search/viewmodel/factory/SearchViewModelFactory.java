package com.demo.search.viewmodel.factory;

import android.arch.lifecycle.ViewModelProvider.Factory;
import android.support.annotation.NonNull;

import com.demo.domain.search.interactors.GetSearchRestaurantUseCase;
import com.demo.search.viewmodel.SearchViewModel;

import javax.inject.Inject;

public class SearchViewModelFactory implements Factory{

    private GetSearchRestaurantUseCase mGetSearchRestaurantUseCase;

    @Inject
    public SearchViewModelFactory(GetSearchRestaurantUseCase getSearchRestaurantUseCase){
        mGetSearchRestaurantUseCase = getSearchRestaurantUseCase;
    }

    @NonNull
    @Override
    public SearchViewModel  create(@NonNull Class modelClass) {
        return new SearchViewModel(mGetSearchRestaurantUseCase);
    }
}
