package com.demo.search.module;

import com.demo.search.presenter.SearchPresenter;
import com.demo.search.presenter.impl.SearchPresenterImpl;
import com.demo.search.activity.SearchActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @Provides
    SearchPresenter.View provideView(SearchActivity searchActivity){
        return searchActivity;
    }

    @Provides
    SearchPresenter provideSearchPresenter(SearchPresenterImpl searchPresenter){
        return searchPresenter;
    }
}
