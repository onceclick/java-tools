package com.janosgyerik.tools.util;

import org.junit.Test;

import java.util.Arrays;

import static com.janosgyerik.tools.util.HeapUtils.heapify;
import static com.janosgyerik.tools.util.HeapUtils.isHeap;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HeapUtils_heapifyTest {
    private void heapifyAndVerify(int[] arr) {
        heapify(arr);
        if (!isHeap(arr)) {
            fail("Not a heap: " + Arrays.toString(arr));
        }
    }

    @Test
    public void test_heapify_3_2_1() {
        heapifyAndVerify(new int[]{3, 2, 1});
    }

    @Test
    public void test_heapify_1_2_3() {
        heapifyAndVerify(new int[]{1, 2, 3});
    }

    @Test
    public void test_heapify_1_2_3_4() {
        heapifyAndVerify(new int[]{1, 2, 3, 4});
    }

    @Test
    public void test_heapify_1_2_4_3() {
        heapifyAndVerify(new int[]{1, 2, 4, 3});
    }

    @Test
    public void test_heapify_1_2_3_4_5() {
        heapifyAndVerify(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void test_heapify_1_2_3_5_7_6_8_4() {
        heapifyAndVerify(new int[]{1, 2, 3, 5, 7, 6, 8, 4});
    }

    @Test
    public void test_heapify_1_2_5_6_7_3_4_8() {
        heapifyAndVerify(new int[]{1, 2, 5, 6, 7, 3, 4, 8});
    }
}
