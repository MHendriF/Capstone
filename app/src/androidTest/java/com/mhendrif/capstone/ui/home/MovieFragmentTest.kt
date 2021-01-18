package com.mhendrif.capstone.ui.home

import android.app.Activity
import android.content.Context
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.R
import com.mhendrif.capstone.data.util.EspressoIdlingResource
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.ui.MovieAdapter
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes = { activity: Activity -> activity.findNavController(R.id.nav_host_fragment).currentDestination }

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadFragmentMovie() {
        val data = mutableListOf<Movie>()

        scenarioRule.scenario.onActivity {
            assertEquals(context.getString(R.string.movie), currentDes(it)?.label)
        }
        Espresso.onView(withId(R.id.rvMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenarioRule.scenario.onActivity { activity ->
            val rvMovie: RecyclerView = activity.findViewById(R.id.rvMovie)
            (rvMovie.adapter as MovieAdapter).currentList.map { data.add(it) }
        }
        assertNotNull(data)
        assertTrue(data.size > 0)
        Espresso.onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(data.size))
        Espresso.onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
    }
}
