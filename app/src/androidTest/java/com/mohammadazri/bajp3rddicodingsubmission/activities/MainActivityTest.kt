package com.mohammadazri.bajp3rddicodingsubmission.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mohammadazri.bajp3rddicodingsubmission.R
import com.mohammadazri.bajp3rddicodingsubmission.ui.activities.MainActivity
import com.mohammadazri.bajp3rddicodingsubmission.utils.DataDummy
import com.mohammadazri.bajp3rddicodingsubmission.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.recyclerViewMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))

    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.recyclerViewTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        onView(withId(R.id.textViewDetailMovieTitleValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieTitleValue)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.textViewDetailMovieYearValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieYearValue)).check(matches(withText(dummyMovie[0].releaseDate)))

        onView(withId(R.id.textViewDetailMovieGenreValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieGenreValue)).check(matches(withText(dummyMovie[0].genres)))

        onView(withId(R.id.textViewDetailMovieDurationValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieDurationValue)).check(matches(withText("${dummyMovie[0].runtime} minutes")))

        onView(withId(R.id.textViewDetailMovieStarValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieStarValue)).check(matches(withText("${dummyMovie[0].rating}")))

        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(withText(dummyMovie[0].overview)))

        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(withText(dummyMovie[0].overview)))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.recyclerViewTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.textViewDetailTvShowTitleValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowTitleValue)).check(matches(withText(dummyTvShow[0].title)))

        onView(withId(R.id.textViewDetailTvShowGenreValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowGenreValue)).check(matches(withText(dummyTvShow[0].genres)))

        onView(withId(R.id.textViewDetailTvShowYearValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowYearValue)).check(matches(withText(dummyTvShow[0].releaseDate)))

        onView(withId(R.id.textViewDetailTvShowStarValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowStarValue)).check(matches(withText("${dummyTvShow[0].rating}")))

        onView(withId(R.id.textViewDetailTvShowEpisodeSeasonValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowEpisodeSeasonValue)).check(matches(withText("${dummyTvShow[0].seasons} Season | ${dummyTvShow[0].episodes} Episode")))

        onView(withId(R.id.textViewDetailTvShowSynopsisValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowSynopsisValue)).check(matches(withText(dummyTvShow[0].overview)))
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.imageViewFavoriteMovie)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        onView(withId(R.id.recyclerViewFavoriteMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewFavoriteMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        onView(withId(R.id.textViewDetailMovieTitleValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieYearValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieGenreValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieDurationValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieStarValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailMovieSynopsisValue)).check(matches(isDisplayed()))

        onView(withId(R.id.imageViewFavoriteMovie)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoriteTvShow() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.recyclerViewTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.imageViewFavoriteTvShow)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.action_favorite)).perform(ViewActions.click())
        onView(withId(R.id.navigation_tvShow)).perform(ViewActions.click())
        onView(withId(R.id.recyclerViewFavoriteTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewFavoriteTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        onView(withId(R.id.textViewDetailTvShowTitleValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowGenreValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowYearValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowStarValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowEpisodeSeasonValue)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewDetailTvShowSynopsisValue)).check(matches(isDisplayed()))

        onView(withId(R.id.imageViewFavoriteTvShow)).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}