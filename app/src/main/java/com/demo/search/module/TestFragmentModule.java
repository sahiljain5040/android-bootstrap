package com.demo.search.module;

import com.demo.search.fragment.TestFragment;
import com.demo.search.presenter.SearchPresenter;
import com.demo.search.presenter.impl.SearchPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TestFragmentModule {

    @Provides
    SearchPresenter.View provideView(TestFragment testFragment){
        return testFragment;
    }

    @Provides
    SearchPresenter provideSearchPresenter(SearchPresenterImpl searchPresenter){
        return searchPresenter;
    }
}
