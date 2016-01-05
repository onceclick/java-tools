package com.janosgyerik.tools.util;

import java.util.*;

public class RankComparator<T> implements Comparator<T> {
    private final Map<T, Integer> indexes;

    private RankComparator(List<T> items) {
        indexes = new HashMap<>(items.size());

        int index = 0;
        for (T item : items) {
            if (indexes.containsKey(item)) {
                throw new IllegalArgumentException("Inconsistent ranks: there should be no duplicates");
            }
            indexes.put(item, ++index);
        }
    }

    public static <T> Comparator<T> fromLowToHigh(List<T> items) {
        return Comparator.nullsFirst(new RankComparator<>(items));
    }

    public static <T> Comparator<T> fromHighToLow(List<T> items) {
        List<T> copy = new ArrayList<>(items);
        Collections.reverse(copy);
        return fromLowToHigh(copy);
    }

    @Override
    public int compare(T o1, T o2) {
        return indexes.get(o1).compareTo(indexes.get(o2));
    }
}
