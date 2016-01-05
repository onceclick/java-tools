package com.janosgyerik.tools.counter.api;

import java.util.Collection;
import java.util.Map;

public interface SortedCounter<T> extends Counter<T> {

    Map.Entry<T, Integer> topEntry();

    T top();

    int topCount();

    Map.Entry<T, Integer> bottomEntry();

    T bottom();

    int bottomCount();

    Map.Entry<T, Integer> nthEntry(int n);

    T nth(int n);

    int nthCount(int n);

    Collection<Map.Entry<T, Integer>> sortedEntrySet();
}
