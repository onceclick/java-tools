package com.janosgyerik.tools.counter.api;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Counter<T> {

    void add(T item);

    void addAll(Collection<T> items);

    Set<Map.Entry<T, Integer>> entrySet();

    int get(T item);
}
