package com.janosgyerik.utils.collections;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Letters {
  private Letters() {
    // utility class, forbidden constructor
  }

  public static List<Character> lowercase() {
    return IntStream.rangeClosed('a', 'z')
      .mapToObj(c -> (char) c)
      .collect(Collectors.toList());
  }
}
