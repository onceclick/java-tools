package com.janosgyerik.utils.misc;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.janosgyerik.utils.misc.IterTools.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class IterToolsTest {

    private <T> Set<List<T>> permutations(List<T> nums) {
        return IterTools.toSet(IterTools.permutations(nums));
    }

    @Test
    public void test_permutate_empty() {
        assertEquals(Collections.singleton(Collections.emptyList()), permutations(Collections.emptyList()));
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
    public void should_get_equal_list_from_iterator() {
        List<Integer> list = Arrays.asList(3, 1, 4, 5, 2);
        assertEquals(list, toList(list.iterator()));
    }

    @Test
    public void should_get_empty_list_from_empty_iterator() {
        List<Integer> list = Collections.emptyList();
        assertEquals(list, toList(list.iterator()));
    }

    @Test
    public void should_get_1_empty_permutation_for_empty_list() {
        Iterable<List<Integer>> permutations = IterTools.permutations(0);
        assertThat(permutations).containsOnly(Collections.emptyList());
    }

    @Test
    public void should_get_1_permutation_for_singleton_list() {
        Set<List<Integer>> permutations = permutations(Collections.singletonList(7));
        assertThat(permutations).containsOnly(Collections.singletonList(7));
    }

    @Test
    public void should_get_2_permutations_for_x_y() {
        Set<List<Character>> permutations = permutations(Arrays.asList('x', 'y'));
        assertThat(permutations).containsOnly(
                Arrays.asList('x', 'y'),
                Arrays.asList('y', 'x')
        );
    }

    @Test
    public void should_get_6_permutations_for_1_2_3() {
        Set<List<Integer>> permutations = permutations(Arrays.asList(1, 2, 3));
        assertThat(permutations).containsOnly(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(3, 2, 1)
        );
    }

    @Test
    public void should_get_equal_permutations_from_recursion_and_iterator() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Set<List<Integer>> set = StreamSupport.stream(IterTools.permutations(6).spliterator(), false).collect(Collectors.toSet());
        assertEquals(set, permutations(list));
    }
}
