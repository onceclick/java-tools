package com.janosgyerik.tools.counter.api;

import java.util.Collection;
import java.util.Map;

public interface Counter<T> {

    void add(T item);

    void addAll(Collection<T> items);

    int get(T item);

    Map<T, Integer> map();
}
