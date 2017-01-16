package com.janosgyerik.utils.counter;

import com.janosgyerik.utils.counter.api.Counter;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class CounterTest {

    @Test
    public void test_add_one() {
        Counter<String> counter = Counters.counter();
        assertTrue(counter.counts().isEmpty());

        counter.add("hello");
        assertFalse(counter.counts().isEmpty());
    }

    @Test
    public void test_add_one_twice() {
        Counter<String> counter = Counters.counter();
        assertTrue(counter.counts().isEmpty());

        counter.add("hello");
        assertEquals(1, counter.counts().size());

        counter.add("hello");
        assertEquals(1, counter.counts().size());
    }

    @Test
    public void testAddAll() {
        Counter<String> counter = Counters.counter();
        counter.addAll(Arrays.asList("hello", "hello", "hello", "world"));
        assertEquals(3, counter.get("hello"));
        assertEquals(1, counter.get("world"));
    }

    @Test
    public void testEntrySet() {
        Counter<String> counter = Counters.counter();
        counter.addAll(Arrays.asList("hello", "hello", "hello", "world"));

        Map<String, Integer> entries = new HashMap<>();
        entries.put("hello", 3);
        entries.put("world", 1);
        assertEquals(entries, counter.counts());
    }

    @Test
    public void testGet() {
        Counter<String> counter = Counters.counter();
        assertEquals(0, counter.get("hello"));

        counter.add("hello");
        assertEquals(1, counter.get("hello"));

        counter.add("hello");
        assertEquals(2, counter.get("hello"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_expose_internal_map_of_counts() {
        Counter<String> counter = Counters.counter();
        counter.counts().put("some key", 7);
    }
}
