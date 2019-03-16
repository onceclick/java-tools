package com.janosgyerik.utils.collections;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
  private Streams() {
    // utility class, forbidden constructor
  }

  public static <T> Stream<T> times(T item, int count) {
    return IntStream.range(0, count)
      .mapToObj(ignored -> item);
  }
}
