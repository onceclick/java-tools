package com.janosgyerik.tools.algorithm.sort;

import com.janosgyerik.tools.util.HeapUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HeapSortTest extends SortTest {
    @Override
    void sort(int[] arr) {
        HeapSort.sort(arr);
    }

    @Test
    public void test_heapify_1_2_3_4_5() {
        int[] arr = {1, 2, 3, 4, 5};
        HeapSort.heapify(arr, arr.length);
        assertTrue(HeapUtils.isHeap(arr));
    }

    @Test
    public void test_heapify_1_2_3_5_7_6_8_4() {
        int[] arr = {1, 2, 3, 5, 7, 6, 8, 4};
        HeapSort.heapify(arr, arr.length);
        assertTrue(HeapUtils.isHeap(arr));
    }

    @Test
    public void test_heapify_1_2_5_6_7_3_4_8() {
        int[] arr = {1, 2, 5, 6, 7, 3, 4, 8};
        HeapSort.heapify(arr, arr.length);
        assertTrue(HeapUtils.isHeap(arr));
    }
}