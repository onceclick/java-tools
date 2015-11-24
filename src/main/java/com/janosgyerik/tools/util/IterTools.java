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
        if (collection.isEmpty()) {
            return Collections.emptySet();
        }

        Queue<PermutationHelper<T>> queue = new LinkedList<>();
        queue.add(new PermutationHelper<>(Collections.emptyList(), new LinkedList<>(collection)));

        while (true) {
            if (queue.peek().choices.isEmpty()) {
                break;
            }
            PermutationHelper<T> helper = queue.poll();
            for (T candidate : helper.choices) {
                List<T> newList = new LinkedList<>(helper.list);
                List<T> newChoices = new LinkedList<>(helper.choices);
                newChoices.remove(candidate);
                newList.add(candidate);
                queue.add(new PermutationHelper<>(newList, newChoices));
            }
        }

        Set<List<T>> result = new HashSet<>();
        for (PermutationHelper<T> helper : queue) {
            result.add(helper.list);
        }
        return result;
    }
}
