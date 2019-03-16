package com.janosgyerik.utils.collections;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class ListsTest {
  @Test
  @UseDataProvider("itemTimesAndExpectedResult")
  public void times_returns_list_of_items_multiplied(Object item, int times, List<Object> list) {
    assertThat(Lists.times(item, times)).isEqualTo(list);
    assertThat(Lists.times("foo", 3)).isEqualTo(Arrays.asList("foo", "foo", "foo"));
  }

  @DataProvider
  public static Object[][] itemTimesAndExpectedResult() {
    return new Object[][]{
      {"foo", 3, Arrays.asList("foo", "foo", "foo")},
      {"foo", 0, Collections.emptyList()},
      {1, 3, Arrays.asList(1, 1, 1)},
    };
  }
}
