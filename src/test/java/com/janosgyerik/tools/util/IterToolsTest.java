package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.IterTools;
import org.junit.Test;

import java.util.Arrays;
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
        assertEquals("[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]",
                permutations(Arrays.asList(1, 2, 3)).toString());
    }
}