package com.demo.presenters;

import com.demo.domain.interactors.GetSearchRestaurantUseCase;
import com.demo.domain.interactors.UseCaseObserver;
import com.demo.domain.model.SearchResult;
import com.demo.presenters.impl.SearchPresenterImpl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
import static org.mockito.Mockito.mock;
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
    @Mock SearchPresenterImpl mSearchPresenter;

    @Rule public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){

        mView = mock(SearchPresenter.View.class);
        mSearchRestaurantUseCase = mock(GetSearchRestaurantUseCase.class);

        mSearchPresenter = new SearchPresenterImpl(mSearchRestaurantUseCase);
    }

    @Test
    public void loadThrowsException_IfViewNotSet() throws Exception {

        exception.expect(Exception.class);
        exception.expectMessage("setView() not called Before calling load()");
        mSearchPresenter.load("");
    }

    @Test
    public void loadHasZeroViewAndUseCaseInteractions_IfViewNotSet(){
        try {
            mSearchPresenter.load("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        verifyZeroInteractions(mView);
        verifyZeroInteractions(mSearchRestaurantUseCase);
    }

    @Test
    public void loadResults_IfViewIsSetAndSuccessfullyGotResults() throws Exception {
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
    public void loadShowError_IfViewIsSetAndGetSearchResultsFailed() throws Exception {
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
