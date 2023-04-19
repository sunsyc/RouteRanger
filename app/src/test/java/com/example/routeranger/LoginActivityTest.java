package com.example.routeranger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.routeranger.ui.activity.LoginActivity;
import com.example.routeranger.ui.fragment.LoginFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@LargeTest
@Config(packageName = "com.example.routeranger", manifest = Config.NONE)
public class LoginActivityTest {

    private ActivityScenario<LoginActivity> activityScenario;

    @Before
    public void setUp() {
        activityScenario = ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void onCreate_initializesFragment() {
        activityScenario.moveToState(Lifecycle.State.CREATED);

        activityScenario.onActivity(activity -> {
            Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.container);
            assertNotNull("Fragment not found", fragment);
            assertTrue("Fragment is not a LoginFragment", fragment instanceof LoginFragment);
        });
    }

    @Test
    public void activityLifecycle() {
        activityScenario.moveToState(Lifecycle.State.CREATED);
        activityScenario.moveToState(Lifecycle.State.RESUMED);
        activityScenario.moveToState(Lifecycle.State.STARTED);
        activityScenario.moveToState(Lifecycle.State.DESTROYED);
    }
}
