package org.danny.demo;

import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class GameTest
{
    Game g = new Game();

    @Test
    public void normal_number() {
        assertEquals("1", g.say(1));
        assertEquals("2", g.say(2));
    }

    @Test
    public void multiples_of_3() {
        assertEquals("Fizz", g.say(3));
        assertEquals("Fizz", g.say(6));
    }

    @Test
    public void multiples_of_5() {
        assertEquals("Buzz", g.say(5));
        assertEquals("Buzz", g.say(10));
    }

    @Test
    public void multiples_of_15()
    {
        assertEquals("FizzBuzz", g.say(15));
    }

    @Test
    public void multiples_of_13() {
        assertEquals("Fizz", g.say(13));
    }

    @Test
    public void multiples_of_52() {
        assertEquals("Buzz", g.say(52));
    }

    @Test
    public void multiples_of_51() {
        assertEquals("FizzBuzz", g.say(51));
    }

    @Test
    public void multiples_of_35() {
        assertEquals("FizzBuzz", g.say(35));
    }



}
