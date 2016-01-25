package com.janosgyerik.tools.counter;

import com.janosgyerik.tools.counter.api.Counter;
import com.janosgyerik.tools.counter.api.SortedCounter;

import java.util.*;

public final class Counters {

    private Counters() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    private static class CounterImpl<T> implements Counter<T> {

        private final Map<T, Integer> counts;

        private CounterImpl() {
            this.counts = new HashMap<>();
        }

        @Override
        public void add(T item) {
            counts.put(item, get(item) + 1);
        }

        @Override
        public void addAll(Collection<T> items) {
            items.forEach(this::add);
        }

        @Override
        public Set<Map.Entry<T, Integer>> entrySet() {
            return counts.entrySet();
        }

        @Override
        public int get(T item) {
            Integer count = counts.get(item);
            return count != null ? count : 0;
        }
    }

    private static class SortedCounterImpl<T> implements SortedCounter<T> {

        private final Counter<T> counter = new CounterImpl<>();

        private final Comparator<Map.Entry<T, Integer>> countComparator =
                (o1, o2) -> Integer.compare(o1.getValue(), o2.getValue());

        private final List<Map.Entry<T, Integer>> sortedEntries = new ArrayList<>();

        private boolean modifiedSinceLastSort;

        private void ensureSortedEntries() {
            if (modifiedSinceLastSort) {
                sortedEntries.clear();
                sortedEntries.addAll(counter.entrySet());
                sortedEntries.sort(countComparator);
                modifiedSinceLastSort = false;
            }
        }

        @Override
        public Map.Entry<T, Integer> firstEntry() {
            ensureSortedEntries();
            return sortedEntries.get(sortedEntries.size() - 1);
        }

        @Override
        public T first() {
            return firstEntry().getKey();
        }

        @Override
        public int firstCount() {
            return firstEntry().getValue();
        }

        @Override
        public Map.Entry<T, Integer> lastEntry() {
            ensureSortedEntries();
            return sortedEntries.get(0);
        }

        @Override
        public T last() {
            return lastEntry().getKey();
        }

        @Override
        public int lastCount() {
            return lastEntry().getValue();
        }

        @Override
        public Map.Entry<T, Integer> nthEntry(int n) {
            ensureSortedEntries();
            return sortedEntries.get(sortedEntries.size() - n);
        }

        @Override
        public T nth(int n) {
            return nthEntry(n).getKey();
        }

        @Override
        public int nthCount(int n) {
            return nthEntry(n).getValue();
        }

        @Override
        public List<Map.Entry<T, Integer>> sortedEntrySet() {
            ensureSortedEntries();
            return sortedEntries;
        }

        @Override
        public void add(T item) {
            counter.add(item);
            modifiedSinceLastSort = true;
        }

        @Override
        public void addAll(Collection<T> items) {
            counter.addAll(items);
            modifiedSinceLastSort = true;
        }

        @Override
        public Set<Map.Entry<T, Integer>> entrySet() {
            return counter.entrySet();
        }

        @Override
        public int get(T item) {
            return counter.get(item);
        }
    }

    public static <T> Counter<T> counter() {
        return new CounterImpl<>();
    }

    public static <T> SortedCounter<T> sortedCounter() {
        return new SortedCounterImpl<>();
    }
}
