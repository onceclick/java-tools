package com.janosgyerik.utils.misc;

import org.junit.Test;

import java.util.*;

import static com.janosgyerik.utils.misc.IterTools.permutations;
import static org.assertj.core.api.Assertions.assertThat;

public class IterToolsTest {

    @Test
    public void should_get_1_empty_permutation_for_empty_list() {
        Iterable<List<Integer>> permutations = permutations(0);
        assertThat(permutations).containsOnlyElementsOf(Collections.singleton(Collections.emptyList()));
    }

    @Test
    public void should_get_1_permutation_for_singleton_list() {
        Iterable<List<Integer>> permutations = permutations(Collections.singletonList(7));
        assertThat(permutations).containsOnlyElementsOf(Collections.singleton(Collections.singletonList(7)));
    }

    @Test
    public void should_get_2_permutations_for_x_y() {
        Iterable<List<Character>> permutations = permutations(Arrays.asList('x', 'y'));
        assertThat(permutations).containsOnlyElementsOf(Arrays.asList(
            Arrays.asList('x', 'y'),
            Arrays.asList('y', 'x')
        ));
    }

    @Test
    public void should_get_6_permutations_for_1_2_3() {
        Iterable<List<Integer>> permutations = permutations(Arrays.asList(1, 2, 3));
        assertThat(permutations).containsOnlyElementsOf(Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(1, 3, 2),
            Arrays.asList(2, 1, 3),
            Arrays.asList(2, 3, 1),
            Arrays.asList(3, 1, 2),
            Arrays.asList(3, 2, 1)
        ));
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_exception_when_iterating_beyond_permutations() {
        Iterator<List<Integer>> iterator = permutations(3).iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next();
    }
}
