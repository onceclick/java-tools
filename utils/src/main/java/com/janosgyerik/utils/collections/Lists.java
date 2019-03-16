package com.janosgyerik.utils.collections;

import java.util.List;
import java.util.stream.Collectors;

public class Lists {
  private Lists() {
    // utility class, forbidden constructor
  }

  public static <T> List<T> times(T item, int count) {
    return Streams.times(item, count)
      .collect(Collectors.toList());
  }
}
