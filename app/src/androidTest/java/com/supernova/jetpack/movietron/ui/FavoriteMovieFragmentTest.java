package com.supernova.jetpack.movietron.ui;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.testing.SingleFragmentActivity;
import com.supernova.jetpack.movietron.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteMovieFragmentTest {

    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final FavoriteMovieFragment favoriteMovieFragment = new FavoriteMovieFragment();

    @Before
    public void setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(favoriteMovieFragment);
    }

    @After
    public void teardown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.recycler_favorite_movie)).check(matches(isDisplayed()));
        // onView(withId(R.id.recycler_movie)).check(new RecyclerViewItemCountAssertion(20));
    }
}