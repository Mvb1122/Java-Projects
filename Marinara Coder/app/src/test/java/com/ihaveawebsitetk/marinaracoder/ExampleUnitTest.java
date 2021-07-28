package com.ihaveawebsitetk.marinaracoder;

import org.junit.Test;

import marinara.Coder;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals("11", Coder.encode('a'));
    }
}