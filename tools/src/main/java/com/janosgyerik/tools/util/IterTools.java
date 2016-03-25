package com.janosgyerik.tools.util;

import java.util.*;

public final class IterTools {

    private IterTools() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    private static class PermutationHelper<T> {
        private final List<T> list;
        private final List<T> choices;

        PermutationHelper(List<T> list, List<T> choices) {
            this.list = list;
            this.choices = choices;
        }
    }

    public static <T> Set<List<T>> permutations(Collection<T> collection) {
        Set<List<T>> result = new LinkedHashSet<>();

        Queue<PermutationHelper<T>> queue = new LinkedList<>();
        queue.add(new PermutationHelper<>(new LinkedList<>(), new LinkedList<>(collection)));

        while (!queue.isEmpty()) {
            PermutationHelper<T> helper = queue.poll();
            for (T candidate : helper.choices) {
                List<T> newList = new LinkedList<>(helper.list);
                List<T> newChoices = new LinkedList<>(helper.choices);
                newChoices.remove(candidate);
                newList.add(candidate);
                if (newList.size() == collection.size()) {
                    result.add(newList);
                } else {
                    queue.add(new PermutationHelper<>(newList, newChoices));
                }
            }
        }
        return result;
    }

    public static <T> Iterator<List<T>> permutationIterator(List<T> list) {
        int size = list.size();
        int maxCount = factorial(size);

        return new Iterator<List<T>>() {
            int count = 0;
            int[] indexes = createInitialIndexes();

            private int[] createInitialIndexes() {
                int[] indexes = new int[size];
                for (int i = 0; i < indexes.length; ++i) {
                    indexes[i] = i;
                }
                return indexes;
            }

            @Override
            public boolean hasNext() {
                return count < maxCount;
            }

            @Override
            public List<T> next() {
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
        };
    }

    private static int factorial(int n) {
        if (n < 2) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        toList(iterator, list);
        return list;
    }

    public static <T> void toList(Iterator<T> iterator, List<T> list) {
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
    }
}
