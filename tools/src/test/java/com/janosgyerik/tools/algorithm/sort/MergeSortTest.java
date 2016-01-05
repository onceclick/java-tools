package com.janosgyerik.tools.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeSortTest extends SortTest {
    @Override
    void sort(int[] arr) {
        MergeSort.sort(arr);
    }

    @Test
    public void testMerge_1_2_3_4() {
        int[] arr = {1, 2, 3, 4};
        int[] copy = arr.clone();
        MergeSort.merge(copy, 0, 2, 4);
        assertEquals(Arrays.toString(arr), Arrays.toString(copy));
    }

    @Test
    public void testMerge_1_2_3_4_5_mid2() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copy = arr.clone();
        MergeSort.merge(copy, 0, 2, 5);
        assertEquals(Arrays.toString(arr), Arrays.toString(copy));
    }

    @Test
    public void testMerge_1_2_3_4_5_mid3() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copy = arr.clone();
        MergeSort.merge(copy, 0, 3, 5);
        assertEquals(Arrays.toString(arr), Arrays.toString(copy));
    }
}