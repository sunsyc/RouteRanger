package com.example.routeranger;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.routeranger.ui.activity.NewRouteActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NewRouteActivityTest {

    @Rule
    public ActivityScenarioRule<NewRouteActivity> activityRule = new ActivityScenarioRule<>(NewRouteActivity.class);

    @Test
    public void testButtonsAreDisplayed() {
        // Check if buttons are displayed on the screen
        Espresso.onView(ViewMatchers.withId(R.id.routeNameButton))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.startDestinationButton))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.endDestinationButton))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.desiredLengthTimeButton))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.generateRoutesButton))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.newroute_back_button))
                .check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.settings_button))
                .check(matches(ViewMatchers.isDisplayed()));
    }

}
