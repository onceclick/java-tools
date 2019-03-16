package com.janosgyerik.utils.algorithms.tree.binarytree;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  public void test_example() {
    List<List<String>> ladders = solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    assertThat(ladders).containsOnly(
      Arrays.asList("hit", "hot", "lot", "log", "cog"),
      Arrays.asList("hit", "hot", "dot", "dog", "cog"));
  }

  @Test
  public void test_unreachable_end() {
    List<List<String>> ladders = solution.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"));
    assertThat(ladders).isEmpty();
  }

  @Test
  public void test_disconnected_list() {
    List<List<String>> ladders = solution.findLadders("hot", "dog", Arrays.asList("hot", "dog"));
    assertThat(ladders).isEmpty();
  }

  @Test
  public void test_red_tax() {
    List<List<String>> ladders = solution.findLadders("red", "tax", Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));
    assertThat(ladders).containsOnly(
      Arrays.asList("red", "rex", "tex", "tax"),
      Arrays.asList("red", "ted", "tad", "tax"),
      Arrays.asList("red", "ted", "tex", "tax"));
  }

  @Test
  public void test_lost_miss() {
    List<List<String>> ladders = solution.findLadders("lost", "miss", Arrays.asList("most", "mist", "miss", "lost", "fist", "fish"));
    assertThat(ladders).containsOnly(Arrays.asList("lost", "most", "mist", "miss"));
  }

  @Test
  public void test_talk_tail() {
    List<List<String>> ladders = solution.findLadders("talk", "tail", Arrays.asList("talk", "tons", "fall", "tail", "gale", "hall", "negs"));
    assertThat(ladders).isEmpty();
  }
}
