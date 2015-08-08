package com.janosgyerik.tools.counter;

import com.janosgyerik.tools.counter.api.Counter;
import com.janosgyerik.tools.counter.api.SortedCounter;

import java.util.*;

public class Counters {

    private Counters() {
        // utility class, forbidden constructor
    }

    private static class CounterImpl<T> implements Counter<T> {

        private final Map<T, Integer> counts;

        private CounterImpl() {
            this.counts = new HashMap<>();
        }

        @Override
        public void add(T item) {
            Integer count = counts.get(item);
            if (count == null) {
                count = 0;
            }
            counts.put(item, count + 1);
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

        private volatile boolean modifiedSinceLastBuild;

        void ensureSortedEntries() {
            if (modifiedSinceLastBuild) {
                synchronized (this) {
                    sortedEntries.clear();
                    sortedEntries.addAll(counter.entrySet());
                    sortedEntries.sort(countComparator);
                    modifiedSinceLastBuild = false;
                }
            }
        }

        @Override
        public Map.Entry<T, Integer> topEntry() {
            ensureSortedEntries();
            return sortedEntries.get(sortedEntries.size() - 1);
        }

        @Override
        public T top() {
            return topEntry().getKey();
        }

        @Override
        public int topCount() {
            return topEntry().getValue();
        }

        @Override
        public Map.Entry<T, Integer> bottomEntry() {
            ensureSortedEntries();
            return sortedEntries.get(0);
        }

        @Override
        public T bottom() {
            return bottomEntry().getKey();
        }

        @Override
        public int bottomCount() {
            return bottomEntry().getValue();
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
        public Collection<Map.Entry<T, Integer>> sortedEntrySet() {
            ensureSortedEntries();
            return sortedEntries;
        }

        @Override
        public void add(T item) {
            counter.add(item);
            modifiedSinceLastBuild = true;
        }

        @Override
        public void addAll(Collection<T> items) {
            counter.addAll(items);
            modifiedSinceLastBuild = true;
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
