package com.demo.ui.activity;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.demo.androidbootstrap.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

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
        mSearchQuery = "Bakery";

        onView(withId(R.id.seach_edit_text))
                .perform(typeText(mSearchQuery), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.search_btn))
                .perform(click());
        onView(withId(R.id.loading_container))
                .check(ViewAssertions.matches(not(isDisplayed())));
        onView(withId(R.id.no_results_container))
                .check(ViewAssertions.matches(not(isDisplayed())));

        onView(withId(R.id.seach_edit_text))
                .perform(clearText(), typeText("Invalid Search Query"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.search_btn))
                .perform(click());
        onView(withId(R.id.loading_container))
                .check(ViewAssertions.matches(not(isDisplayed())));
        onView(withId(R.id.no_results_container))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}
