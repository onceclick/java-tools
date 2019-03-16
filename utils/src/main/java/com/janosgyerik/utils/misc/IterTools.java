package com.janosgyerik.utils.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Utility methods related to iterators.
 */
public final class IterTools {

  private IterTools() {
    throw new AssertionError("utility class, forbidden constructor");
  }

  /**
   * Return an iterable over all permutations of a list's elements.
   * Note that there are n! permutations, where n is the size of the list.
   *
   * @param list items to permutate
   * @param <T>  type of list items
   * @return iterable of all permutations
   */
  public static <T> Iterable<List<T>> permutations(List<T> list) {
    return () -> new PermutationIterator<>(list);
  }

  /**
   * Return an iterable over all permutations of the numbers 1..n.
   *
   * @param n the upper bound of the range of values to use
   * @return iterable of all permutations
   */
  public static Iterable<List<Integer>> permutations(int n) {
    List<Integer> nums = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
    return permutations(nums);
  }

  private static int factorial(int n) {
    if (n < 2) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  /**
   * Convert an iterator to list.
   *
   * @param iterator the iterator to convert
   * @param <T>      type of the elements in the iterator
   * @return list of values from the iterator
   */
  public static <T> List<T> toList(Iterator<T> iterator) {
    return StreamSupport
      .stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
      .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Convert an iterator to a list, by iterating into (appending to) the given list.
   *
   * @param iterator the iterator to convert
   * @param list     the list to iterate (append) into
   * @param <T>      type of the elements in the iterator
   */
  public static <T> void toList(Iterator<T> iterator, List<T> list) {
    iterator.forEachRemaining(list::add);
  }

  /**
   * Convert an iterable to a set.
   *
   * @param iterable the iterable to convert
   * @param <T>      type of the elements in the iterable
   * @return set of values from the iterable
   */
  public static <T> Set<T> toSet(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());
  }

  private static class PermutationIterator<T> implements Iterator<List<T>> {
    private final List<T> list;
    private final int size;
    private final int maxCount;
    private final int[] indexes;

    int count = 0;

    PermutationIterator(List<T> list) {
      this.list = list;
      this.size = list.size();
      int factorial = factorial(size);
      maxCount = factorial >= size ? factorial : Integer.MAX_VALUE;
      indexes = createInitialIndexes();
    }

    private int[] createInitialIndexes() {
      return IntStream.range(0, size).toArray();
    }

    @Override
    public boolean hasNext() {
      return count < maxCount;
    }

    @Override
    public List<T> next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      List<T> current = new ArrayList<>(size);
      for (int index : indexes) {
        current.add(list.get(index));
      }

      if (++count < maxCount) {
        updateIndexes();
      }

      return current;
    }

    private void updateIndexes() {
      int i = indexes.length - 2;
      for (; i >= 0; --i) {
        if (indexes[i] < indexes[i + 1]) {
          break;
        }
      }
      int j = indexes.length - 1;
      for (;; j--) {
        if (indexes[j] > indexes[i]) {
          break;
        }
      }

      swap(i, j);

      int half = (indexes.length - i) / 2;
      for (int k = 1; k <= half; ++k) {
        swap(i + k, indexes.length - k);
      }
    }

    private void swap(int i, int j) {
      int tmp = indexes[i];
      indexes[i] = indexes[j];
      indexes[j] = tmp;
    }
  }
}
