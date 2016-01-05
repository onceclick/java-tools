package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.MultiKey;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MultiKeyTest {
    @Test
    public void test_SinglePart_Equals() {
        MultiKey key1 = new MultiKey(3);
        MultiKey key2 = new MultiKey(3);
        assertEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_NotEquals() {
        MultiKey key1 = new MultiKey(3);
        MultiKey key2 = new MultiKey(4);
        assertNotEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_NotEqualsWithNull() {
        MultiKey key1 = new MultiKey(3);
        MultiKey key2 = new MultiKey();
        assertNotEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_NotEqualsWithNullElement() {
        MultiKey key1 = new MultiKey(3);
        MultiKey key2 = new MultiKey(new Object[]{null});
        assertNotEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_Equals_BothNullElement() {
        MultiKey key1 = new MultiKey();
        MultiKey key2 = new MultiKey();
        assertEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_Equals_BothNull() {
        MultiKey key1 = new MultiKey(new Object[]{null});
        MultiKey key2 = new MultiKey(new Object[]{null});
        assertEquals(key1, key2);
    }

    @Test
    public void test_SinglePart_NotEquals_DifferentKindsOfNull() {
        MultiKey key1 = new MultiKey();
        MultiKey key2 = new MultiKey(new Object[]{null});
        MultiKey key3 = new MultiKey((Object[])null);
        assertNotEquals(key1, key2);
        assertNotEquals(key1, key3);
        assertNotEquals(key2, key3);
    }

    @Test
    public void test_ThreePart_Equals() {
        int number = 3;
        String string = "hello";
        Date date = new Date();
        MultiKey key1 = new MultiKey(number, string, date);
        MultiKey key2 = new MultiKey(number, string, date);
        assertEquals(key1, key2);
    }

    @Test
    public void test_ThreePart_NotEquals_IfAnyDifference() {
        int number = 3;
        String string = "hello";
        Date date = new Date();
        MultiKey key1 = new MultiKey(number, string, date);
        assertEquals(key1, new MultiKey(number, string, date));
        assertNotEquals(key1, new MultiKey(number + 1, string, date));
        assertNotEquals(key1, new MultiKey(number, string + "x", date));
        assertNotEquals(key1, new MultiKey(number, string, new Date(date.getTime() + 1)));
    }

    @Test
    public void test_ThreePart_NotEquals_IfAnyNull() {
        int number = 3;
        String string = "hello";
        Date date = new Date();
        MultiKey key1 = new MultiKey(number, string, date);
        assertEquals(key1, new MultiKey(number, string, date));
        assertNotEquals(key1, new MultiKey(null, string, date));
        assertNotEquals(key1, new MultiKey(number, null, date));
        assertNotEquals(key1, new MultiKey(number, string, null));
    }

    @Test
    public void test_ThreePart_NotEquals_IfIncomplete() {
        int number = 3;
        String string = "hello";
        Date date = new Date();
        MultiKey key1 = new MultiKey(number, string, date);
        assertEquals(key1, new MultiKey(number, string, date));
        assertNotEquals(key1, new MultiKey(number, string));
        assertNotEquals(key1, new MultiKey(number));
        assertNotEquals(key1, new MultiKey());
    }

    @Test
    public void test_ThreePart_ToString() {
        int number = 3;
        double precision = 1.23;
        String string = "hello";
        MultiKey key1 = new MultiKey(number, string, precision);
        assertEquals("MultiKey{keys=[3, hello, 1.23]}", key1.toString());
    }
}
