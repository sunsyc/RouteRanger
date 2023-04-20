package com.example.routeranger;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.routeranger.ui.activity.HomePageActivity;
import com.example.routeranger.ui.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomePageActivityTest {

    @Rule
    public ActivityScenarioRule<HomePageActivity> activityRule = new ActivityScenarioRule<>(HomePageActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testLoginButton_click_switchesToLoginActivity() {
        // Check if the login button is displayed on the screen
        Espresso.onView(withId(R.id.login_button))
                .check(matches(isDisplayed()))
                .check(matches(withText("Log in")));

        // Click on the login button
        Espresso.onView(withId(R.id.login_button)).perform(ViewActions.click());

        // Check if LoginActivity is started after clicking the login button
        intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));
    }

}
