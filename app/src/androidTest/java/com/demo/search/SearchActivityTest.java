package com.demo.search;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.demo.androidbootstrap.R;
import com.demo.search.activity.SearchActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by sahil on 4/5/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest {

    private String mSearchQuery;

    @Rule
    public ActivityTestRule<SearchActivity> mActivityRule = new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void setUp(){
    }

    @Test
    public void typeValidSearchQueryInInput_clickSearchButton_loadsResult(){

        onView(withId(R.id.seach_edit_text))
                .perform(clearText(), typeText("Invalid Search Query"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.search_btn))
                .perform(click());
        onView(withId(R.id.loading_container))
                .check(ViewAssertions.matches(not(isDisplayed())));
        onView(withId(R.id.no_results_container))
                .check(ViewAssertions.matches(isDisplayed()));

        mSearchQuery = "Bakery";
        onView(withId(R.id.seach_edit_text))
                .perform(clearText(), typeText(mSearchQuery), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.search_btn))
                .perform(click());
        onView(withId(R.id.loading_container))
                .check(ViewAssertions.matches(not(isDisplayed())));
        onView(withId(R.id.no_results_container))
                .check(ViewAssertions.matches(not(isDisplayed())));

        onView(withId(R.id.search_results))
                .perform(actionOnItemAtPosition(20, click()));
    }
}
