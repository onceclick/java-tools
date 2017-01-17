package com.janosgyerik.utils.counter;

import com.janosgyerik.utils.counter.api.Counter;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest {

    @Test
    public void test_add_one() {
        Counter<String> counter = Counters.counter();
        assertThat(counter.counts()).isEmpty();

        counter.add("hello");
        assertThat(counter.counts()).isNotEmpty();
    }

    @Test
    public void test_add_one_twice() {
        Counter<String> counter = Counters.counter();
        String item = "hello";

        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(1);
        assertThat(counter.counts().size()).isEqualTo(1);

        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(2);
        assertThat(counter.counts().size()).isEqualTo(1);
    }

    @Test
    public void testAddAll() {
        Counter<String> counter = Counters.counter();
        String item1 = "hello";
        String item2 = "there";
        counter.addAll(Arrays.asList(item1, item1, item1, item2));
        assertThat(counter.get(item1)).isEqualTo(3);
        assertThat(counter.get(item2)).isEqualTo(1);
    }

    @Test
    public void testGet() {
        Counter<String> counter = Counters.counter();
        String item = "hello";
        assertThat(counter.get(item)).isEqualTo(0);

        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(1);

        counter.add(item);
        assertThat(counter.get(item)).isEqualTo(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_not_expose_internal_map_of_counts() {
        Counter<String> counter = Counters.counter();
        counter.counts().put("some key", 7);
    }
}
