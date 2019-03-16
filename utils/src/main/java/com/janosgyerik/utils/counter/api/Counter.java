package com.janosgyerik.utils.counter.api;

import java.util.Map;

/**
 * Build a map of counts of objects.
 * Use the Counters utility class to obtain different specialized implementations.
 *
 * @param <T> type of the counted objects
 */
public interface Counter<T> {

  /**
   * Add an item, incrementing its count.
   *
   * @param item the item to count
   */
  Counter<T> add(T item);

  /**
   * Add many items, incrementing their count.
   *
   * @param items the items to count
   */
  Counter<T> addAll(Iterable<T> items);

  /**
   * Return the count of specified item.
   * If the item was never added to the counter, return 0.
   *
   * @param item the item whose count to find
   * @return the count of the item
   */
  int get(T item);

  /**
   * Return the counts as a map.
   * Note that for maximum usability, some implementations may expose
   * the internal map of counts.
   *
   * @return the counts as a map
   */
  Map<T, Integer> counts();
}
