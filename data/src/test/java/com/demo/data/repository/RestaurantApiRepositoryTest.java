package com.demo.data.repository;

import com.demo.domain.interactors.UseCaseObserver;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;

/**
 * Created by sahil on 4/7/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class RestaurantApiRepositoryTest {

    @Mock
    SearchApi mSearchApi;
    @InjectMocks
    RestaurantApiRepository mRestaurantApiRepository;

    @Before
    public void setUp(){

    }

    @Test
    public void testGetRestaurants_withValidApiResponse_ReturnsRestaurantWrapperList() throws FileNotFoundException {
        //Given
        given(mSearchApi.getRestaurantsObservable(anyString(), anyMapOf(String.class, String.class)))
                .willReturn(Observable.just(getSearchApiResponse()));

        //When
        final List<RestaurantWrapper> restaurantWrapperList = new ArrayList<>();
        mRestaurantApiRepository.getRestaurants(new HashMap<String, String>())
                .subscribe(new UseCaseObserver<List<RestaurantWrapper>>(){
                    @Override
                    public void onNext(List<RestaurantWrapper> restaurantWrappers) {
                        super.onNext(restaurantWrappers);
                        restaurantWrapperList.addAll(restaurantWrappers);
                    }
                });

        //Then
        assertThat(getActualRestaurantList().size(), is(restaurantWrapperList.size()));
    }

    private String getRestaurantsJsonFilePath(){
        String filePath = new File("").getAbsolutePath();
        return filePath.concat("/data/src/test/java/com/demo/data/assets/restaurants.json");
    }

    private ApiSearchRestaurantsResponse getSearchApiResponse() throws FileNotFoundException {
        return new GsonBuilder().create()
                .fromJson(new JsonReader(
                        new FileReader(getRestaurantsJsonFilePath())),
                        ApiSearchRestaurantsResponse.class);
    }

    private List<RestaurantWrapper> getActualRestaurantList(){
        List<RestaurantWrapper> restaurantWrapperList = new ArrayList<>();
        RestaurantWrapper restaurantWrapper1 = new RestaurantWrapper();
        Restaurant restaurant1 = new Restaurant();
        restaurantWrapper1.setRestaurant(restaurant1);
        RestaurantWrapper restaurantWrapper2 = new RestaurantWrapper();
        Restaurant restaurant2 = new Restaurant();
        restaurantWrapper2.setRestaurant(restaurant2);
        restaurantWrapperList.add(restaurantWrapper1);
        restaurantWrapperList.add(restaurantWrapper2);
        return restaurantWrapperList;
    }

}
