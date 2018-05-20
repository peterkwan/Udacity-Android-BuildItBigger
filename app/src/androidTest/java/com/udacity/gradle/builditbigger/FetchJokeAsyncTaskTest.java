package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.FetchJokeAsyncTask.FetchJokeListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class FetchJokeAsyncTaskTest {

    String joke = null;
    CountDownLatch signal = null;

    @Before
    public void setUp() {
        signal = new CountDownLatch(1);
    }

    @Test
    public void testFetchJokeTask() throws Exception {
        FetchJokeAsyncTask task = new FetchJokeAsyncTask(new FetchJokeListener() {
            @Override
            public void onJokeFetched(String s) {
                joke = s;
                signal.countDown();
            }
        }, null);
        task.execute();
        signal.await(30, TimeUnit.SECONDS);

        assertNotNull(joke);
    }

    @Test
    public void testFetchJokeTask2() throws Exception {
        FetchJokeAsyncTask task = new FetchJokeAsyncTask(new FetchJokeListener() {
            @Override
            public void onJokeFetched(String s) {
                joke = s;
                signal.countDown();
            }
        }, null) {
            @Override
            protected String doInBackground(Void... voids) {
                return "This is a testing joke.";
            }
        };
        task.execute();
        signal.await(30, TimeUnit.SECONDS);

        assertEquals("This is a testing joke.", joke);
    }

}
