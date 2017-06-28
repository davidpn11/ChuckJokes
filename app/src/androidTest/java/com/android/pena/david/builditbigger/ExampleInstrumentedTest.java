package com.android.pena.david.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity2> mActivityTestRule =
            new ActivityTestRule<>(MainActivity2.class);

    @Test
    public void checkJokeRetrival() throws Exception{

        String joke = EndpointsAsyncTask.jokeText;

        onView(withId(R.id.joke_btn)).perform(click());

        while(joke == null){
            joke = EndpointsAsyncTask.jokeText;
        }
        assertNotEquals(joke,"");
    }


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.android.pena.david.builditbigger", appContext.getPackageName());
    }



}
