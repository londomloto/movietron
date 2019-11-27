package com.supernova.jetpack.movietron.ui;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.supernova.jetpack.movietron.R;
import com.supernova.jetpack.movietron.data.FakeDataSource;
import com.supernova.jetpack.movietron.data.source.local.entity.ItemEntity;
import com.supernova.jetpack.movietron.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailActivityTest {
    private final ItemEntity dummyItem = FakeDataSource.generateMovieItems().get(0);

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.EXTRA_TYPE, ItemEntity.TYPE_MOVIE);
            result.putExtra(DetailActivity.EXTRA_ID, dummyItem.getId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.txt_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_detail_title)).check(matches(withText(dummyItem.getTitle())));

        onView(withId(R.id.txt_detail_release)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_detail_release)).check(matches(withText(dummyItem.getRelease())));

        onView(withId(R.id.txt_detail_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_detail_overview)).check(matches(withText(dummyItem.getOverview())));

        onView(withId(R.id.img_detail_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.img_detail_backdrop)).check(matches(isDisplayed()));

//        onView(allOf(withId(R.id.img_detail_poster), withTagValue(is((Object)dummyItem.getPoster())))).check(matches(isDisplayed()));
    }
}