package com.janosgyerik.utils.counter.api;

import java.util.Collection;
import java.util.Map;

/**
 * Build a map of counts of objects.
 *
 * @param <T> type of the counted objects
 */
public interface Counter<T> {

    /**
     * Add an item, incrementing its count.
     *
     * @param item the item to count
     */
    void add(T item);

    /**
     * Add a collection of items, incrementing their count.
     *
     * @param items the items to count
     */
    void addAll(Collection<T> items);

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
     *
     * @return the counts as a map
     */
    Map<T, Integer> counts();
}
