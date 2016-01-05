package com.janosgyerik.tools.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeapUtils_isHeapTest {
    @Test
    public void is_max_heap_1_2_3() {
        assertFalse(HeapUtils.isHeap(new int[]{1, 2, 3}));
    }

    @Test
    public void is_max_heap_2_1_3() {
        assertFalse(HeapUtils.isHeap(new int[]{2, 1, 3}));
    }

    @Test
    public void is_max_heap_2_3_1() {
        assertFalse(HeapUtils.isHeap(new int[]{2, 3, 1}));
    }

    @Test
    public void is_max_heap_3_2_1() {
        assertTrue(HeapUtils.isHeap(new int[]{3, 2, 1}));
    }

    @Test
    public void is_max_heap_3_1_2() {
        assertTrue(HeapUtils.isHeap(new int[]{3, 1, 2}));
    }

    @Test
    public void is_max_heap_3_1_2_4() {
        assertFalse(HeapUtils.isHeap(new int[]{3, 1, 2, 4}));
    }

    @Test
    public void is_max_heap_4_3_1_2() {
        assertTrue(HeapUtils.isHeap(new int[]{4, 3, 1, 2}));
    }

    @Test
    public void is_max_heap_3_4_1_2() {
        assertFalse(HeapUtils.isHeap(new int[]{3, 4, 1, 2}));
    }

    @Test
    public void is_max_heap_16_11_15_9_10_13_14_8_2_1_5_12_6_3_7_4() {
        assertTrue(HeapUtils.isHeap(new int[]{16, 11, 15, 9, 10, 13, 14, 8, 2, 1, 5, 12, 6, 3, 7, 4}));
    }

    @Test
    public void is_max_heap_16_11_15_9_10_13_4_8_2_1_5_12_6_3_7_14() {
        assertFalse(HeapUtils.isHeap(new int[]{16, 11, 15, 9, 10, 13, 4, 8, 2, 1, 5, 12, 6, 3, 7, 14}));
    }
}
