package com.janosgyerik.tools.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IterToolsTest {

    private Set<List<Integer>> permutations(List<Integer> integers) {
        return IterTools.permutations(integers);
    }

    @Test
    public void test_permutate_empty() {
        assertEquals("[]", permutations(Arrays.asList()).toString());
    }

    @Test
    public void test_permutate_1() {
        assertEquals("[[1]]", permutations(Arrays.asList(1)).toString());
    }

    @Test
    public void test_permutate_1_2() {
        assertEquals("[[1, 2], [2, 1]]", permutations(Arrays.asList(1, 2)).toString());
    }

    @Test
    public void test_permutate_1_2_3() {
        Set<List<Integer>> result = new HashSet<>();
        result.addAll(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(3, 2, 1)
        ));
        assertEquals(result, permutations(Arrays.asList(1, 2, 3)));
    }
}
