package com.project.castleglobal.contracts;

import com.project.castleglobal.entities.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by sahil on 3/10/18.
 */

public interface SearchRepository {

    Observable<HashMap<String, ArrayList<Restaurant>>> getRestaurants(Map<String, String> queryParams);
}
