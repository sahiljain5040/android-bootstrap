package com.demo.search;

import com.demo.domain.search.interactors.GetSearchRestaurantUseCase;
import com.demo.domain.base.interactors.UseCaseObserver;
import com.demo.domain.search.model.SearchResult;
import com.demo.search.presenter.SearchPresenter;
import com.demo.search.presenter.impl.SearchPresenterImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by sahil on 2/24/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock GetSearchRestaurantUseCase mSearchRestaurantUseCase;
    @Mock SearchPresenter.View mView;
    @InjectMocks SearchPresenterImpl mSearchPresenter;

    @Rule public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
    }

    @Test
    public void testLoad_ViewNotSet_ExceptionIsThrown() throws Exception {

        exception.expect(Exception.class);
        exception.expectMessage("setView() not called Before calling load()");
        mSearchPresenter.load("");
    }

    @Test
    public void testLoad_ViewNotSet_ViewAndUseCaseHasZeroInteractions(){
        try {
            mSearchPresenter.load("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        verifyZeroInteractions(mView);
        verifyZeroInteractions(mSearchRestaurantUseCase);
    }

    @Test
    public void testLoad_ViewSetAndGotSearchResults_ViewAndUseCaseHasPositiveInteractions() throws Exception {
        mSearchPresenter.setView(mView);
        String searchQuery = "";
        final List<SearchResult> searchResults = new ArrayList<>();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DisposableObserver<List<SearchResult>>)invocation.getArguments()[0]).onNext(searchResults);
                return null;
            }
        }).when(mSearchRestaurantUseCase).execute(
                any(UseCaseObserver.class),
                anyMapOf(String.class, String.class));


        mSearchPresenter.load(searchQuery);

        verify(mView, times(1)).onSearchResultsLoading();
        verify(mSearchRestaurantUseCase, times(1)).dispose();
        verify(mView, times(1)).onSearchResultsLoaded(searchResults);
        verify(mView, times(0)).onSearchResultsFailed();
    }

    @Test
    public void testLoad_ViewSetAndSearchResultsFailed_ViewHasNegativeInteractions() throws Exception {
        mSearchPresenter.setView(mView);
        String searchQuery = "";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DisposableObserver<List<SearchResult>>)invocation.getArguments()[0]).onError(new Throwable());
                return null;
            }
        }).when(mSearchRestaurantUseCase).execute(
                any(UseCaseObserver.class),
                anyMapOf(String.class, String.class));


        mSearchPresenter.load(searchQuery);

        verify(mView, times(1)).onSearchResultsLoading();
        verify(mSearchRestaurantUseCase, times(1)).dispose();
        verify(mView, times(0)).onSearchResultsLoaded(any(List.class));
        verify(mView, times(1)).onSearchResultsFailed();
    }
}
