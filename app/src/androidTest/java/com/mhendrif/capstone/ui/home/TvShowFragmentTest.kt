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
import com.mhendrif.capstone.core.util.EspressoIdlingResource
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.ui.TvShowAdapter
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val currentDes =
        { activity: Activity -> activity.findNavController(R.id.nav_host_fragment).currentDestination }

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
    fun loadFragmentTvShow() {
        val data = mutableListOf<TvShow>()

        Espresso.onView(withId(R.id.navigation_tv_show)).perform(ViewActions.click())
        scenarioRule.scenario.onActivity {
            assertEquals(context.getString(R.string.tv_show), currentDes(it)?.label)
        }
        Espresso.onView(withId(R.id.bottom_navigation_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvTvShow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenarioRule.scenario.onActivity { activity ->
            val rvTvShow: RecyclerView = activity.findViewById(R.id.rvTvShow)
            (rvTvShow.adapter as TvShowAdapter).currentList.map { data.add(it) }
        }
        assertNotNull(data)
        assertTrue(data.size > 0)
        Espresso.onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(data.size))
        Espresso.onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
    }
}