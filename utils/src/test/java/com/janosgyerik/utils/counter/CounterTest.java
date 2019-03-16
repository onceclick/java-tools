package com.janosgyerik.utils.counter;

import com.janosgyerik.utils.counter.api.Counter;
import java.util.Arrays;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest {

  @Test
  public void item_never_added_should_have_count_0() {
    assertThat(Counters.create().get("nonexistent")).isEqualTo(0);
  }

  @Test
  public void item_added_once_should_have_count_1() {
    String item = "value";
    Counter<String> counter = Counters.create();
    counter.add(item);
    assertThat(counter.get(item)).isEqualTo(1);
  }

  @Test
  public void item_added_twice_should_have_count_2() {
    String item = "value";
    Counter<String> counter = Counters.create();
    counter.add(item);
    counter.add(item);
    assertThat(counter.get(item)).isEqualTo(2);
  }

  @Test
  public void test_adding_many_at_once() {
    Counter<String> counter = Counters.create();
    String item1 = "hello";
    String item2 = "there";
    counter.addAll(Arrays.asList(item1, item1, item1, item2));
    assertThat(counter.get(item1)).isEqualTo(3);
    assertThat(counter.get(item2)).isEqualTo(1);
  }

  @Test
  public void test_internal_state_is_mutable() {
    Counter<String> counter = Counters.create();
    String item = "value";
    int count = 7;
    counter.counts().put(item, count);
    assertThat(counter.get(item)).isEqualTo(count);
  }

  @Test
  public void create_counter_from_iterable() {
    Counter<String> counter = Counters.create(Arrays.asList("foo", "bar", "foo"));
    assertThat(counter.get("foo")).isEqualTo(2);
    assertThat(counter.get("bar")).isEqualTo(1);
    assertThat(counter.get("baz")).isEqualTo(0);
  }

  @Test
  public void create_counter_from_array_of_objects() {
    Counter<String> counter = Counters.create(new String[] {"foo", "bar", "foo"});
    assertThat(counter.get("foo")).isEqualTo(2);
    assertThat(counter.get("bar")).isEqualTo(1);
    assertThat(counter.get("baz")).isEqualTo(0);
  }

  @Test
  public void create_counter_from_string() {
    Counter<Character> counter = Counters.create("foo");
    assertThat(counter.get('o')).isEqualTo(2);
    assertThat(counter.get('f')).isEqualTo(1);
    assertThat(counter.get('z')).isEqualTo(0);
  }

}
