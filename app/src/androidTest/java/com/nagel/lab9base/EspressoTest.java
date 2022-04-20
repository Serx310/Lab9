package com.nagel.lab9base;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    private final ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public ActivityScenarioRule<MainActivity> getActivityRule() {
        return activityRule;
    }

    @Before
    final void cleanUp(){
        this.clearSharedPrefs();
        ActivityScenario.launch(MainActivity.class);
    }

    @After
    public final void tearDown(){this.activityRule.getScenario().close();}

    public void clearSharedPrefs(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        SharedPreferences sharedPreferences = instrumentation.getTargetContext().getSharedPreferences(
                MainActivity.APP_PREFS, Context.MODE_PRIVATE
        );

        sharedPreferences.edit().clear().commit();
    }
    @Test
    public void AddMilkWorks(){
        clearSharedPrefs();
        Espresso.onView(ViewMatchers.withId(R.id.etItemToAdd))
                .perform(ViewActions.click(), ViewActions.typeText("Milk"));
        Espresso.onView(ViewMatchers.withText("ADD")).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText("Milk")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}

