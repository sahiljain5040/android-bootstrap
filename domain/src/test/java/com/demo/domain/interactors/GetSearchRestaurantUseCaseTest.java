package com.demo.domain.interactors;

import com.demo.domain.model.Restaurant;
import com.demo.domain.model.RestaurantWrapper;
import com.demo.domain.model.SearchResult;
import com.demo.domain.repository.RestaurantRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Mockito.when;

/**
 * Created by sahil on 3/30/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetSearchRestaurantUseCaseTest {

    @Mock RestaurantRepository mRestaurantRepository;
    @InjectMocks GetSearchRestaurantUseCase mGetSearchRestaurantUseCase;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
    }

    @Test
    public void buildUseCaseObservable_IfResultsResturnedByRepositoryAreNull(){
        when(mRestaurantRepository.getRestaurants((anyMapOf(String.class, String.class))))
                .thenReturn(null);

        exception.expect(NullPointerException.class);
        mGetSearchRestaurantUseCase.buildUseCaseObservable(new HashMap<String, String>());
    }

    @Test
    public void buildUseCaseObservable_IfResultsResturnedByRepositoryAreValid(){
        //Given
        given(mRestaurantRepository.getRestaurants((anyMapOf(String.class, String.class))))
                .willReturn(getValidRestaurantListObservable());

        //When
        final List<SearchResult> searchResultList = new ArrayList<>();
        mGetSearchRestaurantUseCase
                .buildUseCaseObservable(new HashMap<String, String>())
                .subscribe(new UseCaseObserver<List<SearchResult>>() {
                    @Override
                    public void onNext(List<SearchResult> searchResults) {
                        super.onNext(searchResults);
                        searchResultList.addAll(searchResults);
                    }
                });

        //Then
        assertThat(searchResultList.size(), is(getValidSearchResults().size()));
    }

    private List<RestaurantWrapper> getValidRestaurantList(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId("18219542");
        restaurant.setCuisines("Bakery");
        RestaurantWrapper restaurantWrapper = new RestaurantWrapper();
        restaurantWrapper.setRestaurant(restaurant);
        List<RestaurantWrapper> restaurantWrapperList = new ArrayList<>();
        restaurantWrapperList.add(restaurantWrapper);
        return restaurantWrapperList;
    }

    private List<SearchResult> getValidSearchResults(){
        List<SearchResult> searchResultList = new ArrayList<>();
        SearchResult searchResult1 = new SearchResult();
        searchResult1.setRestaurant(false);
        searchResult1.setName("Bakery");
        searchResultList.add(searchResult1);
        SearchResult searchResult2 = new SearchResult();
        searchResult2.setRestaurant(true);
        Restaurant restaurant = new Restaurant();
        restaurant.setId("18219542");
        restaurant.setCuisines("Bakery");
        searchResult2.setRestaurant(restaurant);
        searchResultList.add(searchResult2);
        return searchResultList;
    }

    private Observable<List<RestaurantWrapper>> getValidRestaurantListObservable(){
        return Observable.just(getValidRestaurantList());
    }

}

