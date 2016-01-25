package com.janosgyerik.tools.counter.api;

import java.util.List;
import java.util.Map;

public interface SortedCounter<T> extends Counter<T> {

    Map.Entry<T, Integer> firstEntry();

    T first();

    int firstCount();

    Map.Entry<T, Integer> lastEntry();

    T last();

    int lastCount();

    Map.Entry<T, Integer> nthEntry(int n);

    T nth(int n);

    int nthCount(int n);

    List<Map.Entry<T, Integer>> sortedEntrySet();
}
