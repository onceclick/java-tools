package com.janosgyerik.utils.collections;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LettersTest {
  @Test
  public void lowercase_returns_lowercase_ascii_alphabet() {
    List<Character> letters = new ArrayList<>();
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    for (char c : lowercase.toCharArray()) {
      letters.add(c);
    }
    assertThat(Letters.lowercase())
      .hasSize(26)
      .isEqualTo(letters);
  }
}
