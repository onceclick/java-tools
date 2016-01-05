package com.janosgyerik.tools.util;

import java.util.*;

public class IterTools {

    private IterTools() {
        // utility class, forbidden constructor
    }

    private static class PermutationHelper<T> {
        private final List<T> list;
        private final List<T> choices;

        public PermutationHelper(List<T> list, List<T> choices) {
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
}
