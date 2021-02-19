package com.just_jump.coding_calculator

import org.junit.Assert.*
import org.junit.After
import org.junit.Test
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Before
    fun setUp() {

    }

    @After
    fun tearDown(){

    }

    @Test
    fun addition_isCorrect() {

        //  GIVEN
        //  Setup the initial information of the test

        //  WHEN
        //  This part is to run the code what do we want to try

        //  THEN
        //  Here we check if the test was run successful

        assertEquals(4, 2 + 2)
    }
}