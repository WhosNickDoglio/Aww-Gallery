package com.nicholasdoglio.eyebleach.data.remote.Mockito;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by nicholasdoglio on 12/9/17.
 */

public class FailingTest {

    @Test
    public void failingTest() throws Exception {
        assertEquals(1, 3);
    }
}
