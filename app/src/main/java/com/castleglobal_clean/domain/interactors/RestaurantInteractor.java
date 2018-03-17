package com.castleglobal_clean.domain.interactors;

import com.castleglobal_clean.domain.interactors.base.Interactor;
import com.castleglobal_clean.domain.model.RestaurantWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 3/17/18.
 */

public interface RestaurantInteractor extends Interactor{

    void setQueryParams(Map<String, String> queryParams);

    interface Callback{
        void onRestaurantsRetrieved(List<RestaurantWrapper> restaurantList);

        void onRetrievalFailed();
    }
}
