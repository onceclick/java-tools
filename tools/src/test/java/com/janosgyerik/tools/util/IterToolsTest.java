package com.janosgyerik.tools.util;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class IterToolsTest {

    private Set<List<Integer>> permutations(List<Integer> integers) {
        return IterTools.permutations(integers);
    }

    @Test
    public void test_permutate_empty() {
        assertEquals(Collections.<List<Integer>>emptySet(), permutations(Collections.emptyList()));
    }

    @Test
    public void test_permutate_1() {
        assertEquals(Collections.singleton(Collections.singletonList(1)), permutations(Collections.singletonList(1)));
    }

    private Set<List<Integer>> makeSet(List<List<Integer>> lists) {
        Set<List<Integer>> result = new HashSet<>();
        result.addAll(lists);
        return result;
    }

    @Test
    public void test_permutate_1_2() {
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 1))),
                permutations(Arrays.asList(1, 2)));
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(2, 1), Arrays.asList(1, 2))),
                permutations(Arrays.asList(1, 2)));
    }

    @Test
    public void test_permutate_1_2_3() {
        assertEquals(
                makeSet(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                        Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1))),
                permutations(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void test_permutationGenerator_a_b_c() {
        Iterator<List<Character>> it = IterTools.permutationIterator(Arrays.asList('a', 'b', 'c'));
    }
}
