package com.castleglobal_clean.domain.interactors.impl;

import com.castleglobal_clean.domain.executor.Executor;
import com.castleglobal_clean.domain.executor.MainThread;
import com.castleglobal_clean.domain.interactors.RestaurantInteractor;
import com.castleglobal_clean.domain.interactors.base.AbstractInteractor;
import com.castleglobal_clean.domain.model.RestaurantWrapper;
import com.castleglobal_clean.domain.repository.RestaurantRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 3/17/18.
 */

public class RestaurantInteractorImpl extends AbstractInteractor implements RestaurantInteractor{

    private RestaurantInteractor.Callback mCallback;
    private RestaurantRepository mRepository;
    private Map<String, String> mQueryParams;

    public RestaurantInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                    RestaurantInteractor.Callback callback,
                                    RestaurantRepository respository) {
        super(threadExecutor, mainThread);
        this.mCallback = callback;
        this.mRepository = respository;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.mQueryParams = queryParams;
    }

    private void notifyError(){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed();
            }
        });
    }

    private void notifyRestaurantsRecieved(List<RestaurantWrapper> restaurantList){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRestaurantsRetrieved(restaurantList);
            }
        });
    }

    @Override
    public void run() {
        List<RestaurantWrapper> restaurantList = mRepository.getRestaurants(mQueryParams);
        if(restaurantList == null){
            notifyError();
        }else{
            notifyRestaurantsRecieved(restaurantList);
        }
    }
}
