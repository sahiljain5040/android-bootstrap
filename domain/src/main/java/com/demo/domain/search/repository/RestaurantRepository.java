package com.demo.domain.search.repository;

import com.demo.domain.search.model.RestaurantWrapper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by sahil on 3/17/18.
 */

public interface RestaurantRepository {

    Observable<List<RestaurantWrapper>> getRestaurants(Map<String, String> queryParams);
}
