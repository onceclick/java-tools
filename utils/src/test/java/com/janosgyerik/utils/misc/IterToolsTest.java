package com.janosgyerik.utils.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

import static com.janosgyerik.utils.misc.IterTools.permutations;
import static com.janosgyerik.utils.misc.IterTools.toList;
import static com.janosgyerik.utils.misc.IterTools.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class IterToolsTest {

  @Test
  public void should_get_1_empty_permutation_for_empty_list() {
    Iterable<List<Integer>> permutations = permutations(0);
    assertThat(permutations).containsOnlyElementsOf(Collections.singleton(Collections.emptyList()));
  }

  @Test
  public void should_get_1_permutation_for_singleton_list() {
    Iterable<List<Integer>> permutations = permutations(Collections.singletonList(7));
    assertThat(permutations).containsOnlyElementsOf(Collections.singleton(Collections.singletonList(7)));
  }

  @Test
  public void should_get_2_permutations_for_x_y() {
    Iterable<List<Character>> permutations = permutations(Arrays.asList('x', 'y'));
    assertThat(permutations).containsOnlyElementsOf(Arrays.asList(
      Arrays.asList('x', 'y'),
      Arrays.asList('y', 'x')));
  }

  @Test
  public void should_get_6_permutations_for_1_2_3() {
    Iterable<List<Integer>> permutations = permutations(Arrays.asList(1, 2, 3));
    assertThat(permutations).containsOnlyElementsOf(Arrays.asList(
      Arrays.asList(1, 2, 3),
      Arrays.asList(1, 3, 2),
      Arrays.asList(2, 1, 3),
      Arrays.asList(2, 3, 1),
      Arrays.asList(3, 1, 2),
      Arrays.asList(3, 2, 1)));
  }

  @Test(expected = NoSuchElementException.class)
  public void should_throw_exception_when_iterating_beyond_permutations() {
    Iterator<List<Integer>> iterator = permutations(3).iterator();
    while (iterator.hasNext()) {
      iterator.next();
    }
    iterator.next();
  }

  @Test
  public void should_get_equal_list_from_toList_iterator() {
    List<Integer> nums = Arrays.asList(1, 2, 3);
    assertThat(toList(nums.iterator())).isEqualTo(nums);
  }

  @Test
  public void should_get_equal_list_from_toList_iterator_into_list() {
    List<Integer> nums = Arrays.asList(1, 2, 3);
    List<Integer> other = new ArrayList<>();
    toList(nums.iterator(), other);
    assertThat(other).isEqualTo(nums);
  }

  @Test
  public void should_get_equal_set_from_toSet_iterator() {
    List<Integer> nums = Arrays.asList(1, 2, 3, 2);
    Set<Integer> numsSet = new HashSet<>(nums);
    assertThat(toSet(nums)).isEqualTo(numsSet);
  }
}
