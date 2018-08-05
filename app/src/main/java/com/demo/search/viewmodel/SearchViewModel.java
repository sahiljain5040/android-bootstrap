package com.demo.search.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.demo.base.model.Resource;
import com.demo.domain.base.interactors.UseCaseObserver;
import com.demo.domain.search.interactors.GetSearchRestaurantUseCase;
import com.demo.domain.search.model.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel{

    private GetSearchRestaurantUseCase mSearchRestaurantUseCase;
    private MutableLiveData<Resource<List<SearchResult>>> mSearchResults;

    @Inject
    public SearchViewModel(GetSearchRestaurantUseCase getSearchRestaurantUseCase){
        mSearchRestaurantUseCase = getSearchRestaurantUseCase;
    }

    public LiveData<Resource<List<SearchResult>>> getSearchResults(){
        if(mSearchResults == null){
            mSearchResults = new MutableLiveData<>();
        }
        return mSearchResults;
    }

    public void load(String queryString){
        mSearchRestaurantUseCase.dispose();
        mSearchRestaurantUseCase.execute(getSearchListObserver(), getQueryParams(queryString));
        mSearchResults.setValue(Resource.loading(null));
    }

    private UseCaseObserver<List<SearchResult>> getSearchListObserver(){

        return new UseCaseObserver<List<SearchResult>>(){
            @Override
            public void onNext(List<SearchResult> searchResults) {
                super.onNext(searchResults);
                mSearchResults.setValue(Resource.success(searchResults));
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                e.printStackTrace();
                mSearchResults.setValue(Resource.error(e.getMessage(), null));
            }
        };
    }

    private Map<String, String> getQueryParams(String searchQuery) {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("q", searchQuery);
        return queryParams;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mSearchRestaurantUseCase.dispose();
    }
}